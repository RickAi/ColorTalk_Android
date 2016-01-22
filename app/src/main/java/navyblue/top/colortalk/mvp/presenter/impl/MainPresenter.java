package navyblue.top.colortalk.mvp.presenter.impl;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;

import java.util.List;

import navyblue.top.colortalk.mvp.models.Image;
import navyblue.top.colortalk.mvp.models.Moment;
import navyblue.top.colortalk.mvp.presenter.abs.IMainPresenter;
import navyblue.top.colortalk.mvp.view.abs.IMainView;
import navyblue.top.colortalk.rest.models.MomentResponse;
import navyblue.top.colortalk.ui.activities.MomentActivity;
import navyblue.top.colortalk.ui.activities.PictureActivity;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by CIR on 16/1/18.
 */

public class MainPresenter extends BasePresenter<IMainView> implements IMainPresenter {


    @Override
    public void loadMoments(boolean clean) {
        //        mLastVideoIndex = 0;
        // @formatter:off

        Subscription s = sColorTalkService.getMoments()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MomentResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MomentResponse momentResponse) {
                        List<Moment> moments = momentResponse.getData();
                        mBaseView.loadNextSuccess(moments);
                    }
                });
        addSubscription(s);
    }

    @Override
    public void showPicture(Moment moment, View imageView) {
        Image image = moment.getImage();
        Intent intent = PictureActivity.newIntent(mActivity, image.getImageUrl(),
                moment.getText());
        ActivityOptionsCompat optionsCompat
                = ActivityOptionsCompat.makeSceneTransitionAnimation(
                mActivity, imageView, PictureActivity.TRANSIT_PIC);
        try {
            ActivityCompat.startActivity(mActivity, intent,
                    optionsCompat.toBundle());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            mActivity.startActivity(intent);
        }
    }

    @Override
    public void showMoment(Moment moment, View imageView) {
        Image image = moment.getImage();
        Intent intent = new Intent(mActivity, MomentActivity.class);
        intent.putExtra(MomentActivity.EXTRA_IMAGE_URL, image.getImageUrl());
        ActivityOptionsCompat optionsCompat
                = ActivityOptionsCompat.makeSceneTransitionAnimation(
                mActivity, imageView, MomentActivity.TRANSIT_PIC);
        try {
            ActivityCompat.startActivity(mActivity, intent,
                    optionsCompat.toBundle());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            mActivity.startActivity(intent);
        }
    }
}
