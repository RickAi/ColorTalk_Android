package navyblue.top.colortalk.mvp.presenter.abs;

import navyblue.top.colortalk.mvp.view.abs.IMomentView;

/**
 * Created by CIR on 16/1/21.
 */
public interface IMomentPresenter extends IBasePresenter<IMomentView> {

    void startPrivateChat(int userID, String userName);
}
