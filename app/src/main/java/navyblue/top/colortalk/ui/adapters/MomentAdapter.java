
package navyblue.top.colortalk.ui.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import navyblue.top.colortalk.R;
import navyblue.top.colortalk.mvp.models.Moment;
import navyblue.top.colortalk.ui.listeners.OnMomentListener;
import navyblue.top.colortalk.ui.widgets.RatioImageView;
import rx.Observable;

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


    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
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
        viewHolder.card.setTag(moment.getText());

        Glide.with(mContext)
             .load(moment.getImage().getImageUrl())
             .centerCrop()
             .into(viewHolder.momentImage);
    }


    @Override public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);
    }


    @Override public int getItemCount() {
        return mList.size();
    }


    public void setOnMomentClickListener(OnMomentListener onMomentListener) {
        this.mOnMomentListener = onMomentListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.iv_moment_image)
        RatioImageView momentImage;
        @Bind(R.id.tv_moment_text) TextView momentText;
        View card;
        Moment moment;


        public ViewHolder(View itemView) {
            super(itemView);
            card = itemView;
            ButterKnife.bind(this, itemView);
            momentImage.setOnClickListener(this);
            card.setOnClickListener(this);
            momentImage.setOriginalSize(50, 50);
        }


        @Override public void onClick(View v) {
            mOnMomentListener.onTouch(v, momentImage, card, moment);
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
