package navyblue.top.colortalk.mvp.presenter.impl;

import com.socks.library.KLog;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import navyblue.top.colortalk.app.ColorTalkApp;
import navyblue.top.colortalk.db.beans.AccountBean;
import navyblue.top.colortalk.mvp.models.RongToken;
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
                    }
                });
        addSubscription(s);
    }

    @Override
    public void localLogin(String email, String password) {
        Subscription s = sColorTalkService.login(email, password, User.THIRD_FALSE)
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
                    }
                });
        addSubscription(s);
    }

    @Override
    public void loginCheck() {
        if (AccountBean.existAccountCache()) {
            AccountBean account = AccountBean.getCachedAccount();
            ColorTalkApp.sAccount = account;
            initRongConnection();
            KLog.d("loginCheck", ColorTalkApp.sAccount.toString());
        }
    }

    private void cacheLoginAccount(final User user) {
        sColorTalkService.getRongToken(String.valueOf(user.getId()), user.getName(), "temp_url")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RongToken>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RongToken rongToken) {
                        String token = rongToken.getToken();
                        AccountBean.cacheAccountInfo(user, token);
                        ColorTalkApp.sAccount = new AccountBean(user, token);
                        initRongConnection();
                    }
                });
    }

    private void initRongConnection() {
        String token = ColorTalkApp.getRongToken();
        if (mActivity.getApplicationInfo().packageName.equals(ColorTalkApp.getCurProcessName(mActivity.getApplicationContext()))) {
            /**
             * IMKit SDK调用第二步,建立与服务器的连接
             */
            RongIM.connect(token, new RongIMClient.ConnectCallback() {

                /**
                 * Token 错误，在线上环境下主要是因为 Token 已经过期，您需要向 App Server 重新请求一个新的 Token
                 */
                @Override
                public void onTokenIncorrect() {
                    KLog.d("LoginActivity", "--onTokenIncorrect");
                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token
                 */
                @Override
                public void onSuccess(String userid) {
                    KLog.d("LoginActivity", "--onSuccess" + userid);
                    mBaseView.gotoMainActivity();
                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {

                }
            });
        }
    }

}
