package navyblue.top.colortalk.ui.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.AlertDialogWrapper;

import butterknife.Bind;
import butterknife.ButterKnife;
import navyblue.top.colortalk.R;
import navyblue.top.colortalk.app.ColorTalkApp;
import navyblue.top.colortalk.mvp.presenter.abs.IPostMomentPresenter;
import navyblue.top.colortalk.mvp.presenter.impl.PostMomentPresenter;
import navyblue.top.colortalk.mvp.view.abs.IPostMomentView;
import navyblue.top.colortalk.ui.base.ToolbarActivity;

/**
 * Created by CIR on 16/1/19.
 */
public class MomentPostActivity extends ToolbarActivity implements IPostMomentView, View.OnClickListener {

    @Bind(R.id.editContent)
    EditText mEditContent;
    @Bind(R.id.btnCamera)
    LinearLayout mBtnCamera;
    @Bind(R.id.btnEmotion)
    LinearLayout mBtnEmotion;
    @Bind(R.id.btnMention)
    LinearLayout mBtnMention;
    @Bind(R.id.btnTrends)
    LinearLayout mBtnTrends;
    @Bind(R.id.btnSend)
    LinearLayout mBtnSend;
    @Bind(R.id.rv_selected_image)
    ImageView mRvSelectedImage;
    @Bind(R.id.btnOverflow)
    LinearLayout mBtnOverflow;
    @Bind(R.id.layBtns)
    LinearLayout mLayBtns;

    private IPostMomentPresenter mPostMomentPresenter;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_moment_post;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        mPostMomentPresenter = new PostMomentPresenter();
        mPostMomentPresenter.attachView(this);
        setUpListeners();
    }

    private void setupToolbar() {
        final ActionBar ab = getActionBarToolbar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private void setUpListeners() {
        mBtnCamera.setOnClickListener(this);
        mBtnEmotion.setOnClickListener(this);
        mBtnMention.setOnClickListener(this);
        mBtnTrends.setOnClickListener(this);
        mBtnSend.setOnClickListener(this);
    }

    @Override
    public void onFailure(Throwable e) {

    }

    @Override
    public void chooseImageDialog() {
        new AlertDialogWrapper.Builder(this)
                .setItems(R.array.post_moment_images, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            mPostMomentPresenter.pickPicture();
                        } else {
                            mPostMomentPresenter.capturePicture();
                        }
                    }
                }).show();
    }

    @Override
    public void showSelectedImage(Bitmap bitmap) {
        mRvSelectedImage.setVisibility(View.VISIBLE);
        mRvSelectedImage.setScaleType(ImageView.ScaleType.FIT_XY);
        mRvSelectedImage.setAdjustViewBounds(true);
        mRvSelectedImage.setImageBitmap(bitmap);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCamera:
                chooseImageDialog();
                break;
            case R.id.btnEmotion:
                break;
            case R.id.btnMention:
                break;
            case R.id.btnTrends:
                break;
            case R.id.btnSend:
                String text = mEditContent.getText().toString();
                mPostMomentPresenter.postMoment(ColorTalkApp.getLoginedUserID(), text);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPostMomentPresenter.onActivityForResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public boolean canBack() {
        return true;
    }
}
