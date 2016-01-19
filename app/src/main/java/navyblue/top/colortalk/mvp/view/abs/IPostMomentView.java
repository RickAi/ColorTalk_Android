package navyblue.top.colortalk.mvp.view.abs;

import android.graphics.Bitmap;

/**
 * Created by CIR on 16/1/19.
 */
public interface IPostMomentView extends IBaseView {
    public void chooseImageDialog();
    public void showSelectedImage(Bitmap bitmap);
}
