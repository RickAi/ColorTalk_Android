package navyblue.top.colortalk.ui.activities;

import android.os.Bundle;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import navyblue.top.colortalk.R;
import navyblue.top.colortalk.ui.base.ToolbarActivity;

/**
 * Created by CIR on 16/1/23.
 */
public class ChatActivity extends ToolbarActivity {


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_chat;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initConnect();
    }

    private void initConnect() {
        String token = "";
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {

            }

            @Override
            public void onSuccess(String s) {

            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        });
    }
}
