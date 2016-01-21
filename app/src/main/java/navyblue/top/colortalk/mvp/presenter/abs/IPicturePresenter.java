package navyblue.top.colortalk.mvp.presenter.abs;

import navyblue.top.colortalk.mvp.view.abs.IPictureView;

/**
 * Created by CIR on 16/1/21.
 */
public interface IPicturePresenter extends IBasePresenter<IPictureView> {
    public void shareImage();
    public void saveImageToGallery(String imageUrl);
}
