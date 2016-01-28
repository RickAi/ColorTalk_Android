package navyblue.top.colortalk.mvp.presenter.abs;

import java.io.File;

import navyblue.top.colortalk.mvp.view.abs.ILoginView;

/**
 * Created by CIR on 16/1/13.
 */
public interface ILoginPresenter extends IBasePresenter<ILoginView> {

    void thirdLogin(String uid);
    void localLogin(String email, String password);
    void loginCheck();
    File copyVideoFile();

}
