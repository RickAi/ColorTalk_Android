package navyblue.top.colortalk.mvp.presenter.impl;

import io.rong.imkit.RongIM;
import navyblue.top.colortalk.mvp.presenter.abs.IMomentPresenter;
import navyblue.top.colortalk.mvp.view.abs.IMomentView;

/**
 * Created by CIR on 16/1/21.
 */
public class MomentPresenter extends BasePresenter<IMomentView> implements IMomentPresenter {

    @Override
    public void startPrivateChat(int userID, String userName) {
        if (RongIM.getInstance() != null)
            RongIM.getInstance().startPrivateChat(mActivity, String.valueOf(userID), "title");
    }
}
