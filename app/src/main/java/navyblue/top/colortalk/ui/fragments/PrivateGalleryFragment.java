package navyblue.top.colortalk.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import navyblue.top.colortalk.R;
import navyblue.top.colortalk.mvp.presenter.abs.IGalleryPresenter;
import navyblue.top.colortalk.mvp.presenter.impl.GalleryPresenter;
import navyblue.top.colortalk.mvp.view.abs.IGalleryView;
import navyblue.top.colortalk.ui.base.BaseFragment;
import navyblue.top.colortalk.ui.widgets.scrollview.MediaInfo;
import navyblue.top.colortalk.ui.widgets.scrollview.ScrollGalleryView;

/**
 * Created by CIR on 16/1/26.
 */
public class PrivateGalleryFragment extends BaseFragment implements IGalleryView {

    @Bind(R.id.scroll_gallery_view)
    ScrollGalleryView mScrollGalleryView;
    @Bind(R.id.pb_loading)
    ProgressBar mPbLoading;

    private IGalleryPresenter mGalleryPresenter;

    public static PrivateGalleryFragment newInstance() {
        return new PrivateGalleryFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflateAndBind(inflater, container, R.layout.fragment_private_gallery);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mGalleryPresenter = new GalleryPresenter();
        mGalleryPresenter.attachView(this, mActivity);

        startLoading();
        mGalleryPresenter.loadPrivateImage();
    }

    @Override
    public void onFailure(Throwable e) {
        Toast.makeText(mActivity, e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void initScrollGallery(List<MediaInfo> images) {
        endLoading();
        FragmentActivity fragmentActivity = (FragmentActivity) mActivity;
        mScrollGalleryView
                .setThumbnailSize(300)
                .setZoom(true)
                .setFragmentManager(fragmentActivity.getSupportFragmentManager())
                .addMedia(images);
    }

    @Override
    public void startLoading() {
        mPbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void endLoading() {
        mPbLoading.setVisibility(View.GONE);
    }
}
