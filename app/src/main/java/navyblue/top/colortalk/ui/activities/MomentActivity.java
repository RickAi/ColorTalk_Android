package navyblue.top.colortalk.ui.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import navyblue.top.colortalk.R;
import navyblue.top.colortalk.mvp.models.Moment;
import navyblue.top.colortalk.mvp.presenter.abs.IMomentPresenter;
import navyblue.top.colortalk.mvp.presenter.impl.MomentPresenter;
import navyblue.top.colortalk.mvp.view.abs.IMomentView;
import navyblue.top.colortalk.ui.base.ToolbarActivity;

public class MomentActivity extends ToolbarActivity implements IMomentView {
    public static final String TRANSIT_PIC = "picture";
    public static final String EXTRA_IMAGE_URL = "image_url";
    public static final String EXTRA_USER_ID = "user_id";

    @Bind(R.id.rv_comments)
    RecyclerView mRvComments;
    @Bind(R.id.iv_moment_image)
    ImageView mIvMomentImage;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;
    IMomentPresenter mMomentPresenter;
    Moment moment;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_moment;
    }


    @Override
    public boolean canBack() {
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        initParams();
        ViewCompat.setTransitionName(mIvMomentImage, TRANSIT_PIC);
        Picasso.with(this).load(getIntent().getStringExtra(EXTRA_IMAGE_URL)).into(mIvMomentImage);
        initMoment();
        initListeners();
    }

    private void initMoment() {
        Intent intent = getIntent();
        moment = new Moment();
        moment.setUserId(intent.getIntExtra(EXTRA_USER_ID, 0));
    }

    private void initListeners() {
        mIvMomentImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int userID = moment.getUserId();
                mMomentPresenter.startPrivateChat(userID, "temp");
            }
        });
    }

    private void initParams() {
        mMomentPresenter = new MomentPresenter();
        mMomentPresenter.attachView(this);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            hideOrShowToolbar();
        } else {
            hideOrShowToolbar();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_moment, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onResume() {
        super.onResume();
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
        ButterKnife.unbind(this);
    }

    @Override
    public void onFailure(Throwable e) {

    }
}
