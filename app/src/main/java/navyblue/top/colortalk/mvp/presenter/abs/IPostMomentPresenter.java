package navyblue.top.colortalk.mvp.presenter.abs;

import android.content.Intent;

import navyblue.top.colortalk.mvp.view.abs.IPostMomentView;

/**
 * Created by CIR on 16/1/19.
 */
public interface IPostMomentPresenter extends IBasePresenter<IPostMomentView> {

    public void pickPicture();
    public void capturePicture();
    public void onActivityForResult(int requestCode, int resultCode, Intent data);

}
