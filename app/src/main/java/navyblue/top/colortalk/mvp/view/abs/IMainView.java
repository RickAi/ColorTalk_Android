package navyblue.top.colortalk.mvp.view.abs;

import java.util.List;

import navyblue.top.colortalk.mvp.models.Moment;

/**
 * Created by CIR on 16/1/18.
 */
public interface IMainView extends IBaseView {

    void loadNextSuccess(List<Moment> moments);
    void loadNextFailed();

}
