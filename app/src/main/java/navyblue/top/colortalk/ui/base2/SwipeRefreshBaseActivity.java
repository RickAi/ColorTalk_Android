package navyblue.top.colortalk.ui.base2;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.widget.SwipeRefreshLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import navyblue.top.colortalk.R;
import navyblue.top.colortalk.ui.widgets.MultiSwipeRefreshLayout;

/**
 * Created by drakeet on 1/3/15.
 */
public abstract class SwipeRefreshBaseActivity extends ToolbarActivity {

    @Bind(R.id.swipe_refresh_layout) public MultiSwipeRefreshLayout mSwipeRefreshLayout;
    private boolean mIsRequestDataRefresh = false;


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        ButterKnife.bind(this);
    }


    @Override protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        trySetupSwipeRefresh();
    }


    void trySetupSwipeRefresh() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setColorSchemeResources(R.color.blue,
                    R.color.purple, R.color.red);
            // do not use lambda!!
            mSwipeRefreshLayout.setOnRefreshListener(
                    new SwipeRefreshLayout.OnRefreshListener() {
                        @Override public void onRefresh() {
                            requestDataRefresh();
                        }
                    });
        }
    }


    public void requestDataRefresh() {
        mIsRequestDataRefresh = true;
    }


    public void setRequestDataRefresh(boolean requestDataRefresh) {
        if (mSwipeRefreshLayout == null) {
            return;
        }
        if (!requestDataRefresh) {
            mIsRequestDataRefresh = false;
            // 防止刷新消失太快，让子弹飞一会儿.
            mSwipeRefreshLayout.postDelayed(new Runnable() {
                @Override public void run() {
                    if (mSwipeRefreshLayout != null) {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }
            }, 1000);
        }
        else {
            mSwipeRefreshLayout.setRefreshing(true);
        }
    }


    public void setProgressViewOffset(boolean scale, int start, int end) {
        mSwipeRefreshLayout.setProgressViewOffset(scale, start, end);
    }


    public void setSwipeableChildren(MultiSwipeRefreshLayout.CanChildScrollUpCallback canChildScrollUpCallback) {
        mSwipeRefreshLayout.setCanChildScrollUpCallback(canChildScrollUpCallback);
    }


    public boolean isRequestDataRefresh() {
        return mIsRequestDataRefresh;
    }
}
