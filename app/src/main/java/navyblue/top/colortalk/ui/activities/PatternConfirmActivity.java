package navyblue.top.colortalk.ui.activities;

import android.text.TextUtils;

import java.util.List;

import me.zhanghai.android.patternlock.ConfirmPatternActivity;
import me.zhanghai.android.patternlock.PatternUtils;
import me.zhanghai.android.patternlock.PatternView;

/**
 * Created by CIR on 16/1/26.
 */
public class PatternConfirmActivity extends ConfirmPatternActivity {
    public final static int REQUEST_PATTERN_CONFIRM = 1;

    @Override
    protected boolean isStealthModeEnabled() {
        // TODO: Return the value from SharedPreferences.
        return false;
    }

    @Override
    protected boolean isPatternCorrect(List<PatternView.Cell> pattern) {
        // TODO: Get saved pattern sha1.
        String patternSha1 = null;
        return TextUtils.equals(PatternUtils.patternToSha1String(pattern), patternSha1);
    }

    @Override
    protected void onForgotPassword() {

        // Finish with RESULT_FORGOT_PASSWORD.
        super.onForgotPassword();
    }
}
