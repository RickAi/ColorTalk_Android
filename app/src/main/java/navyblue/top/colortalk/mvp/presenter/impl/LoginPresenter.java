package navyblue.top.colortalk.mvp.presenter.impl;

import com.socks.library.KLog;

import navyblue.top.colortalk.app.ColorTalkApp;
import navyblue.top.colortalk.db.beans.AccountBean;
import navyblue.top.colortalk.mvp.models.User;
import navyblue.top.colortalk.mvp.presenter.abs.ILoginPresenter;
import navyblue.top.colortalk.mvp.view.abs.ILoginView;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by CIR on 16/1/13.
 */
public class LoginPresenter extends BasePresenter<ILoginView> implements ILoginPresenter {

    @Override
    public void thirdLogin(String uid) {
        Subscription s = sColorTalkService.login(uid, User.THIRD_TRUE)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(User user) {
                        cacheLoginAccount(user);
                        mBaseView.gotoMainActivity();
                    }
                });
        addSubscription(s);
    }

    @Override
    public void localLogin(String email, String password) {

    }

    @Override
    public void loginCheck() {
        if(AccountBean.existAccountCache()){
            AccountBean account = AccountBean.getCachedAccount();
            ColorTalkApp.sAccount = account;
            KLog.d("loginCheck", ColorTalkApp.sAccount.toString());
            mBaseView.gotoMainActivity();
        }
    }

    private void cacheLoginAccount(User user){
        AccountBean.cacheAccountInfo(user);
        ColorTalkApp.sAccount = new AccountBean(user);
        KLog.d("loginCheck", ColorTalkApp.sAccount.toString());
    }

}
