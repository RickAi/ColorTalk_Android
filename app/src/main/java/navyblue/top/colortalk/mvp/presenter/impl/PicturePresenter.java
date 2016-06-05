package navyblue.top.colortalk.mvp.presenter.impl;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import com.squareup.picasso.Picasso;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import navyblue.top.colortalk.R;
import navyblue.top.colortalk.app.Constants;
import navyblue.top.colortalk.mvp.presenter.abs.IPicturePresenter;
import navyblue.top.colortalk.mvp.view.abs.IPictureView;
import navyblue.top.colortalk.util.ToastUtils;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by CIR on 16/1/21.
 */
public class PicturePresenter extends BasePresenter<IPictureView> implements IPicturePresenter {

    @Override
    public void shareImage(final String imageUrl, final String imageDesc) {
        saveImageAndGetPathObservable(mActivity, imageUrl, imageDesc)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(uri -> shareImage(mActivity, uri, "share the image to...", imageUrl, imageDesc),
                        error -> ToastUtils.showLong(error.getMessage()));
    }

    private void shareImage(Context context, Uri uri, String title, String imageUrl, String imageDesc) {
        final SHARE_MEDIA[] displaylist = new SHARE_MEDIA[]
                {
                        SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE,SHARE_MEDIA.SINA,
                        SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE,SHARE_MEDIA.DOUBAN
                };
        new ShareAction(mActivity).setDisplayList( displaylist )
                .withText(imageDesc)
                .withTitle("ColorTalk image share test")
                .withTargetUrl("http://navyblue.top")
                .withMedia(new UMImage(mActivity, imageUrl))
                .setListenerList(new UMShareListener() {
                    @Override
                    public void onResult(SHARE_MEDIA share_media) {
                        ToastUtils.showShort("Shared success!");
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media) {

                    }
                })
                .open();
    }

    @Override
    public void saveImageToGallery(String imageUrl) {
        String imageName = String.valueOf(System.currentTimeMillis());
        Subscription s = saveImageAndGetPathObservable(mActivity, imageUrl, imageName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(uri -> {
                    File appDir = new File(Environment.getExternalStorageDirectory(), Constants.BASE_FILE_DIR);
                    String msg = String.format(mActivity.getString(R.string.picture_has_save_to),
                            appDir.getAbsolutePath());
                    ToastUtils.showShort(msg);
                }, error -> ToastUtils.showLong("\nTry again..."));
        addSubscription(s);
    }

    private Observable<Uri> saveImageAndGetPathObservable(Context context, String url, String title) {
        return Observable.create(new Observable.OnSubscribe<Bitmap>() {
            @Override public void call(Subscriber<? super Bitmap> subscriber) {
                Bitmap bitmap = null;
                try {
                    bitmap = Picasso.with(context).load(url).get();
                } catch (IOException e) {
                    subscriber.onError(e);
                }
                if (bitmap == null) {
                    subscriber.onError(new Exception("Cannot download the image!"));
                }
                subscriber.onNext(bitmap);
                subscriber.onCompleted();
            }
        }).flatMap(bitmap -> {
            File appDir = new File(Environment.getExternalStorageDirectory(), Constants.BASE_FILE_DIR);
            if (!appDir.exists()) {
                appDir.mkdir();
            }
            String fileName = title.replace('/', '-') + ".jpg";
            File file = new File(appDir, fileName);
            try {
                FileOutputStream fos = new FileOutputStream(file);
                assert bitmap != null;
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Uri uri = Uri.fromFile(file);
            // 通知图库更新
            Intent scannerIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
            context.sendBroadcast(scannerIntent);
            return Observable.just(uri);
        }).subscribeOn(Schedulers.io());
    }
}
