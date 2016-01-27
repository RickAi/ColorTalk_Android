package navyblue.top.colortalk.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import navyblue.top.colortalk.R;
import navyblue.top.colortalk.ui.base.ToolbarActivity;
import navyblue.top.colortalk.util.AppUtils;
import navyblue.top.colortalk.util.PatternLockUtils;

/**
 * Created by CIR on 16/1/26.
 */
public class PatternLockActivity extends ToolbarActivity {
    private static final String KEY_CONFIRM_STARTED = "confirm_started";

    private boolean mConfirmStarted = false;

    @Override
    public boolean canBack() {
        return true;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_pattern_lock;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mConfirmStarted = savedInstanceState.getBoolean(KEY_CONFIRM_STARTED);
        }
        if (!mConfirmStarted) {
            PatternLockUtils.confirmPatternIfHas(this);
            mConfirmStarted = true;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean(KEY_CONFIRM_STARTED, mConfirmStarted);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                AppUtils.navigateUp(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (PatternLockUtils.checkConfirmPatternResult(this, requestCode, resultCode)) {
            mConfirmStarted = false;
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
