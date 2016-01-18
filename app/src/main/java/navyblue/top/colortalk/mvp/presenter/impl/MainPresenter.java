package navyblue.top.colortalk.mvp.presenter.impl;

import java.util.List;

import navyblue.top.colortalk.mvp.models.Moment;
import navyblue.top.colortalk.mvp.presenter.abs.IMainPresenter;
import navyblue.top.colortalk.mvp.view.abs.IMainView;
import navyblue.top.colortalk.rest.models.MomentResponse;
import rx.Subscriber;
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

        sColorTalkService.getMoments()
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
    }
}
