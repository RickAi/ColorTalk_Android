package navyblue.top.colortalk.ui.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

public class LoginVideoView extends VideoView {
    public LoginVideoView(Context context) {
        super(context);
    }

    public LoginVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoginVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
            setMeasuredDimension(widthSize, heightSize);
        } else{
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
