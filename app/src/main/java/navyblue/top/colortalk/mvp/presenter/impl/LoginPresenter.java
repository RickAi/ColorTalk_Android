package navyblue.top.colortalk.mvp.presenter.impl;

import navyblue.top.colortalk.mvp.presenter.abs.ILoginPresenter;
import navyblue.top.colortalk.mvp.view.abs.ILoginView;

/**
 * Created by CIR on 16/1/13.
 */
public class LoginPresenter implements ILoginPresenter {

    private ILoginView mLoginView;

    public LoginPresenter(ILoginView loginView){
        this.mLoginView = loginView;
    }

}
