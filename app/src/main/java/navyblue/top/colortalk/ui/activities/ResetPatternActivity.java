package navyblue.top.colortalk.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import navyblue.top.colortalk.R;
import navyblue.top.colortalk.ui.base.ToolbarActivity;
import navyblue.top.colortalk.util.PatternLockUtils;
import navyblue.top.colortalk.util.ToastUtils;

public class ResetPatternActivity extends ToolbarActivity {

    @Bind(R.id.ok_button)
    Button mOkButton;
    @Bind(R.id.cancel_button)
    Button mCancelButton;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_reset_pattern;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PatternLockUtils.clearPattern(ResetPatternActivity.this);
                ToastUtils.show(R.string.pattern_reset, ResetPatternActivity.this);
                finish();
            }
        });

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
