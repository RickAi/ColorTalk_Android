package navyblue.top.colortalk.mvp.presenter.impl;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import navyblue.top.colortalk.mvp.models.Moment;
import navyblue.top.colortalk.mvp.presenter.abs.IPostMomentPresenter;
import navyblue.top.colortalk.mvp.view.abs.IPostMomentView;
import navyblue.top.colortalk.util.QiniuUploadUitls;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by CIR on 16/1/19.
 */
public class PostMomentPresenter extends BasePresenter<IPostMomentView> implements IPostMomentPresenter {

    public static final int IMAGE_REQUEST_GALLERY = 1;
    public static final int IMAGE_REQUEST_CAMERA = 2;
    private Bitmap currentBitmap;
    private File currentFile;

    @Override
    public void pickPicture() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        mActivity.startActivityForResult(intent, IMAGE_REQUEST_GALLERY);
    }

    @Override
    public void capturePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        currentFile = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(currentFile));
        mActivity.startActivityForResult(intent, IMAGE_REQUEST_CAMERA);
    }

    // TODO: 使用新线程进行文件解析与存储
    @Override
    public void onActivityForResult(int requestCode, int resultCode, Intent data) {
        InputStream stream = null;

        if (requestCode == IMAGE_REQUEST_GALLERY && resultCode == Activity.RESULT_OK) {
            try {
                stream = mActivity.getContentResolver().openInputStream(data.getData());
                currentBitmap = BitmapFactory.decodeStream(stream);
                stream.close();

                mBaseView.showSelectedImage(currentBitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (requestCode == IMAGE_REQUEST_CAMERA && resultCode == Activity.RESULT_OK) {
            currentBitmap = decodeSampledBitmapFromFile(currentFile.getAbsolutePath(), 1000, 700);
            mBaseView.showSelectedImage(currentBitmap);
        }
    }

    public static Bitmap decodeSampledBitmapFromFile(String path, int reqWidth, int reqHeight) {
        //First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        // Calculate inSampleSize, Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        int inSampleSize = 1;
        if (height > reqHeight) {
            inSampleSize = Math.round((float) height / (float) reqHeight);
        }
        int expectedWidth = width / inSampleSize;

        if (expectedWidth > reqWidth) {
            //if(Math.round((float)width / (float)reqWidth) > inSampleSize) // If bigger SampSize..
            inSampleSize = Math.round((float) width / (float) reqWidth);
        }
        options.inSampleSize = inSampleSize;
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(path, options);
    }

    @Override
    public void postMoment(final int userID, final String text) {
        // upload the image to qiniu server first
        if (currentBitmap == null) {
            mBaseView.onFailure(new Exception("have not choose a image yet!"));
            return;
        }

        QiniuUploadUitls.getInstance().uploadImage(currentBitmap, new QiniuUploadUitls.QiniuUploadUitlsListener() {

            @Override
            public void onSucess(String fileUrl) {
                // then, record the moment file to our server
                createMoment(userID, getImageName(fileUrl), text);
            }

            @Override
            public void onProgress(int progress) {

            }

            @Override
            public void onError(int errorCode, String msg) {
                Toast.makeText(mActivity, "failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getImageName(String url) {
        int start = url.lastIndexOf('/') + 1;
        int end = url.length();
        return url.substring(start, end);
    }

    private void createMoment(int userID, String imageName, String text) {
        sColorTalkService.createMoment(String.valueOf(userID), imageName, text)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Moment>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Moment moment) {
                        Toast.makeText(mActivity, "success!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
