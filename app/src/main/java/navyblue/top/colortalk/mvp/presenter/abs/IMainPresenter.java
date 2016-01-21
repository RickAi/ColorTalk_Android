package navyblue.top.colortalk.mvp.presenter.abs;

import android.view.View;

import navyblue.top.colortalk.mvp.models.Moment;
import navyblue.top.colortalk.mvp.view.abs.IMainView;

/**
 * Created by CIR on 16/1/18.
 */

public interface IMainPresenter extends IBasePresenter<IMainView> {
    public void loadMoments(boolean clean);
    public void showPicture(Moment moment, View imageView);
    public void showMoment(Moment moment, View imageView);
}
