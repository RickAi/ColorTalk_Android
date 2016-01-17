package navyblue.top.colortalk.ui.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import navyblue.top.colortalk.R;
import navyblue.top.colortalk.mvp.models.Image;
import navyblue.top.colortalk.mvp.models.Moment;
import navyblue.top.colortalk.ui.adapters.MomentAdapter;
import navyblue.top.colortalk.ui.base.SwipeRefreshBaseActivity;
import navyblue.top.colortalk.ui.listeners.OnMomentListener;

public class MainActivity extends SwipeRefreshBaseActivity {

    private static final int PRELOAD_SIZE = 6;

    @Bind(R.id.rv_meizhi)
    RecyclerView mRecyclerView;

    private MomentAdapter mMomentAdapter;
    private List<Moment> mMomentList;
    private boolean mIsFirstTimeTouchBottom = true;
    private int mPage = 1;
    private boolean mMomentBeTouched;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        mMomentList = new ArrayList<>();
//        QueryBuilder query = new QueryBuilder(Meizhi.class);
//        query.appendOrderDescBy("publishedAt");
//        query.limit(0, 10);
//        mMomentList.addAll(App.sDb.query(query));

        setupToolbar();
        setupRecyclerView();
        setupUmeng();
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        new Handler().postDelayed(() -> setRequestDataRefresh(true), 358);
        loadData(true);
    }

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


    private void setupRecyclerView() {
        final StaggeredGridLayoutManager layoutManager
                = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mMomentAdapter = new MomentAdapter(this, mMomentList);
        mRecyclerView.setAdapter(mMomentAdapter);
//        new Once(this).show("tip_guide_6", () -> {
//            Snackbar.make(mRecyclerView, getString(R.string.tip_guide),
//                    Snackbar.LENGTH_INDEFINITE)
//                    .setAction(R.string.i_know, v -> {
//                    })
//                    .show();
//        });

        mRecyclerView.addOnScrollListener(getOnBottomListener(layoutManager));
        mMomentAdapter.setOnMomentClickListener(getOnMomentTouchListener());
    }


    /**
     * 获取服务数据
     *
     * @param clean 清除来自数据库缓存或者已有数据。
     */
    private void loadData(boolean clean) {
        mLastVideoIndex = 0;
        // @formatter:off
//        Subscription s = Observable
//               .zip(sGankIO.getMeizhiData(mPage),
//                     sGankIO.get休息视频Data(mPage),
//                     this::createMeizhiDataWith休息视频Desc)
//               .map(meizhiData -> meizhiData.results)
//               .flatMap(Observable::from)
//               .toSortedList((meizhi1, meizhi2) ->
//                     meizhi2.publishedAt.compareTo(meizhi1.publishedAt))
//               .doOnNext(this::saveMeizhis)
//               .observeOn(AndroidSchedulers.mainThread())
//               .subscribe(meizhis -> {
//                   if (clean) mMomentList.clear();
//                   mMomentList.addAll(meizhis);
//                   mMomentAdapter.notifyDataSetChanged();
//                   setRequestDataRefresh(false);
//               }, throwable -> loadError(throwable));
        // @formatter:on
//        addSubscription(s);
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


    private void loadData() {
        loadData(/* clean */false);
    }


    private OnMomentListener getOnMomentTouchListener() {
        return (v, imageView, card, moment) -> {
            Image image = moment.getImage();

            if (moment == null) return;
            if (v == imageView && !mMomentBeTouched) {
                mMomentBeTouched = true;
                Picasso.with(this).load(image.getImageUrl()).fetch(new Callback() {

                    @Override
                    public void onSuccess() {
                        mMomentBeTouched = false;
//                        startPictureActivity(moment, imageView);
                    }

                    @Override
                    public void onError() {
                        mMomentBeTouched = false;
                    }
                });
            } else if (v == card) {
//                startGankActivity(meizhi.publishedAt);
            }
        };
    }


    private void startGankActivity(Date publishedAt) {
//        Intent intent = new Intent(this, GankActivity.class);
//        intent.putExtra(GankActivity.EXTRA_GANK_DATE, publishedAt);
//        startActivity(intent);
    }


    private void startPictureActivity(Moment meizhi, View transitView) {
//        Intent intent = PictureActivity.newIntent(MainActivity.this, meizhi.url,
//                meizhi.desc);
//        ActivityOptionsCompat optionsCompat
//                = ActivityOptionsCompat.makeSceneTransitionAnimation(
//                MainActivity.this, transitView, PictureActivity.TRANSIT_PIC);
//        try {
//            ActivityCompat.startActivity(MainActivity.this, intent,
//                    optionsCompat.toBundle());
//        } catch (IllegalArgumentException e) {
//            e.printStackTrace();
//            startActivity(intent);
//        }
    }

    @Override
    public void onToolbarClick() {
        mRecyclerView.smoothScrollToPosition(0);
    }

    @OnClick(R.id.main_fab)
    public void onFab(View v) {
        if (mMomentList != null && mMomentList.size() > 0) {
//            startGankActivity(mMomentList.get(0).publishedAt);
        }
    }

    @Override
    public void requestDataRefresh() {
        super.requestDataRefresh();
        mPage = 1;
        loadData(true);
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

    RecyclerView.OnScrollListener getOnBottomListener(final StaggeredGridLayoutManager layoutManager) {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView rv, int dx, int dy) {
                boolean isBottom =
                        layoutManager.findLastCompletelyVisibleItemPositions(
                                new int[2])[1] >=
                                mMomentAdapter.getItemCount() -
                                        PRELOAD_SIZE;
                if (!mSwipeRefreshLayout.isRefreshing() && isBottom) {
                    if (!mIsFirstTimeTouchBottom) {
                        mSwipeRefreshLayout.setRefreshing(true);
                        mPage += 1;
                        loadData();
                    } else {
                        mIsFirstTimeTouchBottom = false;
                    }
                }
            }
        };
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

    private void setupToolbar() {
        final ActionBar ab = getActionBarToolbar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
    }
}
