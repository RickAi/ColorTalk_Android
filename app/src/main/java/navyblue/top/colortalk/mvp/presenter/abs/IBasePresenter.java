
package navyblue.top.colortalk.mvp.presenter.abs;

import navyblue.top.colortalk.mvp.view.abs.IBaseView;

/**
 * Description：Presenter
 * <p/>
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 * <p/>
 * Created by：Yogi Ai
 *
 */

public interface IBasePresenter<V extends IBaseView> {

    void attachView(V baseView);

    void detachView();

}
