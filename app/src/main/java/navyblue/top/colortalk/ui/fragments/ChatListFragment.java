package navyblue.top.colortalk.ui.fragments;

import android.app.Activity;
import android.net.Uri;

import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.model.Conversation;

/**
 * Created by CIR on 16/1/23.
 */
public class ChatListFragment extends ConversationListFragment {

    public static ChatListFragment newInstance(Activity activity) {
        ChatListFragment fragment =  new ChatListFragment();
        Uri uri = Uri.parse("rong://" + activity.getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话非聚合显示
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "true")//设置群组会话聚合显示
                .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")//设置讨论组会话非聚合显示
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "false")//设置系统会话非聚合显示
                .build();
        fragment.setUri(uri);

        return fragment;
    }


}
