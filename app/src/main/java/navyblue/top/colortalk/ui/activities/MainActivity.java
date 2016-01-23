package navyblue.top.colortalk.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import navyblue.top.colortalk.R;
import navyblue.top.colortalk.mvp.models.Moment;
import navyblue.top.colortalk.ui.base.DrawerActivity;
import navyblue.top.colortalk.ui.fragments.MainFragment;

public class MainActivity extends DrawerActivity {

    public final static String TAG = "MainActivity";
    @Bind(R.id.fragment_container)
    FrameLayout mFragmentContainer;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, MainFragment.newInstance()).commit();

        setupToolbar();
        setupUmeng();
    }

    @OnClick(R.id.main_fab)
    public void onFab(View v) {
        Intent intent = new Intent(this, MomentPostActivity.class);
        startActivity(intent);
    }


//    @Override
//    protected void onPostCreate(Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState);
//        new Handler().postDelayed(() -> setRequestDataRefresh(true), 358);
//        mMainPresenter.loadMoments(true);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sample_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                openDrawer();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void setupUmeng() {
//        UmengUpdateAgent.update(this);
//        UmengUpdateAgent.setDeltaUpdate(false);
//        UmengUpdateAgent.setUpdateOnlyWifi(false);
    }

    private void loadError(Throwable throwable) {
//        throwable.printStackTrace();
//        setRequestDataRefresh(false);
//        Snackbar.make(mRecyclerView, R.string.snap_load_fail,
//                Snackbar.LENGTH_LONG).setAction(R.string.retry, v -> {
//            requestDataRefresh();
//        }).show();
    }


    private void saveMeizhis(List<Moment> moments) {
//        App.sDb.insert(meizhis, ConflictAlgorithm.Replace);
    }


//    private MeizhiData createMeizhiDataWith休息视频Desc(MeizhiData data, 休息视频Data love) {
//        Stream.from(data.results)
//              .forEach(meizhi -> meizhi.desc = meizhi.desc + " " +
//                      getFirstVideoDesc(meizhi.publishedAt, love.results));
//        return data;
//    }

    private int mLastVideoIndex = 0;


//    private String getFirstVideoDesc(Date publishedAt, List<Gank> results) {
//        String videoDesc = "";
//        for (int i = mLastVideoIndex; i < results.size(); i++) {
//            Gank video = results.get(i);
//            if (DateUtils.isTheSameDay(publishedAt, video.publishedAt)) {
//                videoDesc = video.desc;
//                mLastVideoIndex = i;
//                break;
//            }
//        }
//        return videoDesc;
//    }

    @Override
    public void onToolbarClick() {
//        mRecyclerView.smoothScrollToPosition(0);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        MenuItem item = menu.findItem(R.id.action_notifiable);
//        initNotifiableItemState(item);
//        return true;
//    }


//    private void initNotifiableItemState(MenuItem item) {
//        PreferencesLoader loader = new PreferencesLoader(this);
//        item.setChecked(loader.getBoolean(R.string.action_notifiable, true));
//    }


//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        switch (id) {
//            case R.id.action_trending:
//                openGitHubTrending();
//                return true;
//            case R.id.action_notifiable:
//                boolean isChecked = !item.isChecked();
//                item.setChecked(isChecked);
//                PreferencesLoader loader = new PreferencesLoader(this);
//                loader.saveBoolean(R.string.action_notifiable, isChecked);
//                ToastUtils.showShort(isChecked
//                        ? R.string.notifiable_on
//                        : R.string.notifiable_off);
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

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

    private void setupToolbar() {
        final ActionBar ab = getActionBarToolbar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
    }

}
