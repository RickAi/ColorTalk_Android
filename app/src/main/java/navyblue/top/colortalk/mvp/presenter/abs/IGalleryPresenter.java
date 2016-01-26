package navyblue.top.colortalk.mvp.presenter.abs;

import navyblue.top.colortalk.mvp.view.abs.IGalleryView;

/**
 * Created by CIR on 16/1/26.
 */
public interface IGalleryPresenter extends IBasePresenter<IGalleryView>  {

    void loadPrivateImage();
}
