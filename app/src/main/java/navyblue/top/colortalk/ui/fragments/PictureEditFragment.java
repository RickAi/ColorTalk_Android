package navyblue.top.colortalk.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

import navyblue.top.colortalk.R;
import navyblue.top.colortalk.mvp.presenter.impl.PictureEditPresenter;
import navyblue.top.colortalk.mvp.view.abs.IPictureEditView;
import navyblue.top.colortalk.ui.base.BaseFragment;
import us.pinguo.edit.sdk.PGEditActivity;
import us.pinguo.edit.sdk.base.PGEditResult;
import us.pinguo.edit.sdk.base.PGEditSDK;
import us.pinguo.edit.sdk.core.utils.BitmapUtils;

/**
 * Created by CIR on 16/3/22.
 */
public class PictureEditFragment extends BaseFragment implements IPictureEditView {

    // 步骤1. 使用Android Studio 导入TUSDK demo， 在手机上运行成功
    // 步骤2. 熟悉demo 的功能，找到我们需要的功能
    // 步骤3. 找到相关的功能代码，试着把他们放到这个类，期间可能需要拷贝类与新建类
    // 步骤4. 反复运行与测试

    //

    private static final int REQUEST_CODE_PICK_PICTURE = 1;
    private String mPicturePath;
    private ImageView mImage;


    private Button mEditBtn;
    private Button mReEditBtn;
    private Button mChoosePhotoBtn;

    private PictureEditPresenter mPresenter;

    public static PictureEditFragment newInstance() {
        return new PictureEditFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflateAndBind(inflater, container, R.layout.fragment_picture_edit);


        mImage = (ImageView) view.findViewById(R.id.img);
        mChoosePhotoBtn = (Button) view.findViewById(R.id.choose_photo_btn);
        mEditBtn = (Button) view.findViewById(R.id.start_edit_btn);
        mReEditBtn = (Button) view.findViewById(R.id.re_edit_btn);

        mChoosePhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startChoosePhoto(v);
            }
        });

        mEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startEdit(v);
            }
        });

        mReEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reEdit(v);
            }
        });

        // TODO: 在这里find views
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onFailure(Throwable e) {

    }

    public void reEdit(View v) {
        enterChoosePhotoState();
    }

    public void startEdit(View v) {
        if (null == mPicturePath) {
            Toast.makeText(mActivity, "Please choose photo first", Toast.LENGTH_SHORT).show();
            return;
        }

        String folderPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
                .getAbsolutePath() + File.separator;
        String outFilePath = folderPath + System.currentTimeMillis() + ".jpg";
        if (mPicturePath.toLowerCase().endsWith("png")) {
            outFilePath = outFilePath.replaceAll("jpg", "png");
        }

        PGEditSDK.instance().startEdit(mActivity, PGEditActivity.class, mPicturePath, outFilePath);


    }

    public void startChoosePhoto(View v) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_CODE_PICK_PICTURE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // todo 解决图片回调 Bug
        Toast.makeText(getContext(), "hi!", Toast.LENGTH_SHORT).show();

        if (requestCode == REQUEST_CODE_PICK_PICTURE
                && resultCode == Activity.RESULT_OK
                && null != data) {

            Uri selectedImage = data.getData();
            String[] filePathColumns = new String[]{MediaStore.Images.Media.DATA};
            Cursor c = mActivity.getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            mPicturePath = c.getString(columnIndex);
            c.close();

            if (null != mPicturePath) {

                enterEditState();

                Bitmap bitmap = BitmapUtils.scalePicture(mPicturePath, 720, true);
                mImage.setImageBitmap(bitmap);
            }

            return;
        }

        //check here
        if (requestCode == PGEditSDK.PG_EDIT_SDK_REQUEST_CODE
                && resultCode == Activity.RESULT_OK) {

            PGEditResult editResult = PGEditSDK.instance().handleEditResult(data);

            mImage.setImageBitmap(editResult.getThumbNail());

            Toast.makeText(mActivity, "Photo saved to:" + editResult.getReturnPhotoPath(), Toast.LENGTH_LONG).show();
            enterReEditState();
        }

        if (requestCode == PGEditSDK.PG_EDIT_SDK_REQUEST_CODE
                && resultCode == PGEditSDK.PG_EDIT_SDK_RESULT_CODE_CANCEL) {
            Toast.makeText(mActivity, "Edit cancelled!", Toast.LENGTH_SHORT).show();
        }

        if (requestCode == PGEditSDK.PG_EDIT_SDK_REQUEST_CODE
                && resultCode == PGEditSDK.PG_EDIT_SDK_RESULT_CODE_NOT_CHANGED) {
            Toast.makeText(mActivity, "Photo do not change!", Toast.LENGTH_SHORT).show();
        }
    }


    private void enterReEditState() {
        mEditBtn.setVisibility(View.GONE);
        mChoosePhotoBtn.setVisibility(View.GONE);

        mReEditBtn.setVisibility(View.VISIBLE);
    }

    private void enterChoosePhotoState() {
        mImage.setVisibility(View.GONE);
        mImage.setImageBitmap(null);


        mReEditBtn.setVisibility(View.GONE);

        //   mEditBtn.setBackgroundResource(R.drawable.sdk_sample_rect_btn_disable);
        mEditBtn.setTextColor(Color.parseColor("#444444"));
        mEditBtn.setVisibility(View.VISIBLE);
        mChoosePhotoBtn.setVisibility(View.VISIBLE);

        mPicturePath = null;
    }

    private void enterEditState() {

        mImage.setVisibility(View.VISIBLE);

        //   mEditBtn.setBackgroundResource(R.drawable.sdk_sample_rect_btn_enable);
        mEditBtn.setTextColor(Color.WHITE);
    }

}
