package navyblue.top.colortalk.ui.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.AlertDialogWrapper;

import butterknife.Bind;
import butterknife.ButterKnife;
import navyblue.top.colortalk.R;
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
        setupToolbar();
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
                        // choose a existing photo
                        if (which == 0) {
                            String[] images = null;
//                            PicturePickFragment.launch(APublishFragment.this, 9, images, 3333);
                        }
                        // take a photo
                        else {

                        }
                    }
                }).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
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
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
