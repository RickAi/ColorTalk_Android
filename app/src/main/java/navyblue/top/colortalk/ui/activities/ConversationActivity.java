package navyblue.top.colortalk.ui.activities;

import android.os.Bundle;

import navyblue.top.colortalk.R;
import navyblue.top.colortalk.ui.base.ToolbarActivity;

/**
 * Created by CIR on 16/1/23.
 */
public class ConversationActivity extends ToolbarActivity {

    @Override
    public boolean canBack() {
        return true;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_conversation;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initToolbar();
    }

    private void initToolbar() {
        getSupportActionBar().setTitle("Chat");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
