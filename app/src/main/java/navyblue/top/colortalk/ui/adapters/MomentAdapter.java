
package navyblue.top.colortalk.ui.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import navyblue.top.colortalk.R;
import navyblue.top.colortalk.mvp.models.Moment;
import navyblue.top.colortalk.rest.ServiceFactory;
import navyblue.top.colortalk.rest.models.UserInfo;
import navyblue.top.colortalk.ui.listeners.OnMomentListener;
import navyblue.top.colortalk.ui.widgets.RatioImageView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MomentAdapter
        extends RecyclerView.Adapter<MomentAdapter.ViewHolder> {

    public static final String TAG = "MomentAdapter";

    private List<Moment> mList;
    private Context mContext;
    private OnMomentListener mOnMomentListener;


    public MomentAdapter(Context context, List<Moment> momentList) {
        mList = momentList;
        mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_moment, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        Moment moment = mList.get(position);
        int limit = 48;
        String text = moment.getText().length() > limit ? moment.getText().substring(0, limit) +
                "..." : moment.getText();
        viewHolder.moment = moment;
        viewHolder.momentText.setText(text);
        if(TextUtils.isEmpty(text)){
            viewHolder.momentText.setVisibility(View.GONE);
        }
        viewHolder.card.setTag(moment.getText());

        ServiceFactory.getColorTalkSingleton().getUserInfo(moment.getUserId())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(UserInfo userInfo) {
                        viewHolder.userNameText.setText(userInfo.getNickname());
                        Glide.with(mContext)
                                .load(userInfo.getIconUrl())
                                .centerCrop()
                                .into(viewHolder.userIconImage);
                    }
                });

        Glide.with(mContext)
                .load(moment.getImage().getImageUrl())
                .centerCrop()
                .into(viewHolder.momentImage)
                .getSize((width, height) -> {
                    if (!viewHolder.card.isShown()) {
                        viewHolder.card.setVisibility(View.VISIBLE);
                    }
                });
    }


    @Override
    public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }


    public void setOnMomentClickListener(OnMomentListener onMomentListener) {
        this.mOnMomentListener = onMomentListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.iv_moment_image)
        RatioImageView momentImage;
        @Bind(R.id.tv_moment_text)
        TextView momentText;
        @Bind(R.id.iv_user_icon)
        ImageView userIconImage;
        @Bind(R.id.tv_user_name)
        TextView userNameText;
        View card;
        Moment moment;

        public ViewHolder(View itemView) {
            super(itemView);
            card = itemView;
            ButterKnife.bind(this, itemView);
            momentImage.setOnClickListener(this);
            userIconImage.setOnClickListener(this);
            momentImage.setOriginalSize(50, 50);
        }


        @Override
        public void onClick(View v) {
            mOnMomentListener.onTouch(v, momentImage, card, moment, userIconImage);
        }
    }


    Observable<Bitmap> getBitmapObservable(String url) {
//        return Observable.defer(() -> {
//            try {
//                return Observable.just(Picasso.with(mContext).load(url).get());
//            } catch (IOException e) {
//                e.printStackTrace();
//                return null;
//            }
//        });
        return null;
    }
}
