package navyblue.top.colortalk.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import navyblue.top.colortalk.R;
import navyblue.top.colortalk.mvp.presenter.abs.IPicturePresenter;
import navyblue.top.colortalk.mvp.presenter.impl.PicturePresenter;
import navyblue.top.colortalk.mvp.view.abs.IPictureView;
import navyblue.top.colortalk.ui.base.ToolbarActivity;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PictureActivity extends ToolbarActivity implements IPictureView {

    public static final String EXTRA_IMAGE_URL = "image_url";
    public static final String EXTRA_IMAGE_TITLE = "image_title";
    public static final String TRANSIT_PIC = "picture";

    @Bind(R.id.picture)
    ImageView mImageView;

    PhotoViewAttacher mPhotoViewAttacher;
    String mImageUrl, mImageTitle;
    IPicturePresenter mPicturePresenter;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_picture;
    }


    @Override
    public boolean canBack() {
        return true;
    }

    public static Intent newIntent(Context context, String url, String desc) {
        Intent intent = new Intent(context, PictureActivity.class);
        intent.putExtra(PictureActivity.EXTRA_IMAGE_URL, url);
        intent.putExtra(PictureActivity.EXTRA_IMAGE_TITLE, desc);
        return intent;
    }


    private void parseIntent() {
        mImageUrl = getIntent().getStringExtra(EXTRA_IMAGE_URL);
        mImageTitle = getIntent().getStringExtra(EXTRA_IMAGE_TITLE);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        mPicturePresenter = new PicturePresenter();
        mPicturePresenter.attachView(this);

        parseIntent();
        // init image view
        ViewCompat.setTransitionName(mImageView, TRANSIT_PIC);
        Picasso.with(this).load(mImageUrl).into(mImageView);
        // set up app bar
        setAppBarAlpha(0.7f);
        setTitle(mImageTitle);
        setupPhotoAttacher();
    }

    private void setupPhotoAttacher() {
        mPhotoViewAttacher = new PhotoViewAttacher(mImageView);
        mPhotoViewAttacher.setOnViewTapListener((view, v, v1) -> hideOrShowToolbar());
        mPhotoViewAttacher.setOnLongClickListener((v) -> showSaveImageDialog());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_picture, menu);
        // TODO: 把图片的一些信息，比如 who，加载到 Overflow 当中
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_share:
                mPicturePresenter.shareImage(mImageUrl, mImageTitle);
                return true;
            case R.id.action_save:
                mPicturePresenter.saveImageToGallery(mImageUrl);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onResume() {
        super.onResume();
        // TODO: 友盟数据分析
//        MobclickAgent.onResume(this);
    }


    @Override
    public void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPhotoViewAttacher.cleanup();
        ButterKnife.unbind(this);
    }

    @Override
    public void onFailure(Throwable e) {

    }

    @Override
    public boolean showSaveImageDialog() {
        new AlertDialog.Builder(PictureActivity.this)
                .setMessage(getString(R.string.ask_saving_picture))
                .setNegativeButton(android.R.string.cancel,
                        (dialog, which) -> dialog.dismiss())
                .setPositiveButton(android.R.string.ok,
                        (dialog, which) -> {
                            mPicturePresenter.saveImageToGallery(mImageUrl);
                            dialog.dismiss();
                        })
                .show();
        return true;
    }

}
