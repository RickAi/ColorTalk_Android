package navyblue.top.colortalk.mvp.presenter.impl;

import java.util.ArrayList;
import java.util.List;

import navyblue.top.colortalk.app.ColorTalkApp;
import navyblue.top.colortalk.mvp.models.Image;
import navyblue.top.colortalk.mvp.presenter.abs.IGalleryPresenter;
import navyblue.top.colortalk.mvp.view.abs.IGalleryView;
import navyblue.top.colortalk.rest.models.ImageResponse;
import navyblue.top.colortalk.ui.widgets.scrollview.MediaInfo;
import navyblue.top.colortalk.ui.widgets.scrollview.PicassoImageLoader;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by CIR on 16/1/26.
 */
public class GalleryPresenter extends BasePresenter<IGalleryView> implements IGalleryPresenter {



    @Override
    public void loadPrivateImage() {
        Subscription s = sColorTalkService.getPrivateImages(String.valueOf(ColorTalkApp.getUserID()))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ImageResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mBaseView.onFailure(e);
                    }

                    @Override
                    public void onNext(ImageResponse imageResponse) {
                        List<Image> images = imageResponse.getData();
                        mBaseView.initScrollGallery(convertToMeidas(images));
                    }
                });
        addSubscription(s);
    }

    private List<MediaInfo> convertToMeidas(List<Image> images){
        List<MediaInfo> medias = new ArrayList<>(images.size());
        for (Image image:  images) {
            medias.add(MediaInfo.mediaLoader(new PicassoImageLoader(image.getImageUrl())));
        }
        return medias;
    }
}
