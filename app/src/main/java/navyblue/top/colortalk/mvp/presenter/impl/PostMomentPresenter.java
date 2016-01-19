package navyblue.top.colortalk.mvp.presenter.impl;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import navyblue.top.colortalk.mvp.presenter.abs.IPostMomentPresenter;
import navyblue.top.colortalk.mvp.view.abs.IPostMomentView;

/**
 * Created by CIR on 16/1/19.
 */
public class PostMomentPresenter extends BasePresenter<IPostMomentView> implements IPostMomentPresenter {

    public static final int IMAGE_REQUEST_GALLERY = 1;
    public static final int IMAGE_REQUEST_CAMERA = 2;
    private Bitmap currentBitmap;

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
        mActivity.startActivityForResult(intent, IMAGE_REQUEST_CAMERA);
    }

    // TODO: 使用新线程进行文件解析与存储
    @Override
    public void onActivityForResult(int requestCode, int resultCode, Intent data) {
        InputStream stream = null;
        if (currentBitmap != null) {
            currentBitmap.recycle();
        }

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
        } else if(requestCode == IMAGE_REQUEST_CAMERA && resultCode == Activity.RESULT_OK){
            currentBitmap = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            currentBitmap.compress(Bitmap.CompressFormat.JPEG, 60, bytes);

            File destination = new File(Environment.getExternalStorageDirectory(),
                    System.currentTimeMillis() + ".jpg");

            FileOutputStream fo;
            try {
                destination.createNewFile();
                fo = new FileOutputStream(destination);
                fo.write(bytes.toByteArray());
                fo.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            mBaseView.showSelectedImage(currentBitmap);
        }
    }
}
