package navyblue.top.colortalk.mvp.presenter.abs;

import navyblue.top.colortalk.mvp.view.abs.IMainView;

/**
 * Created by CIR on 16/1/18.
 */

public interface IMainPresenter extends IBasePresenter<IMainView> {
    public void loadMoments(boolean clean);
}
