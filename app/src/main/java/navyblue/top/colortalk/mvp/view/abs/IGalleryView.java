package navyblue.top.colortalk.mvp.view.abs;

import java.util.List;

import navyblue.top.colortalk.ui.widgets.scrollview.MediaInfo;

/**
 * Created by CIR on 16/1/26.
 */
public interface IGalleryView extends IBaseView {

    void initScrollGallery(List<MediaInfo> images);
    void startLoading();
    void endLoading();
}
