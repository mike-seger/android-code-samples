package com.example.customdialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomDialog extends Dialog {
    private Activity mActivity;
    private ImageView ivClose;
    private Button btnYes;
    private Button btnNo;
    private TextView mtvMessage;
    private ImageView mivIcon;
    CustomDialogCompleteListener mListerner = null;
    private String msMsg = "";
    private String msBtnYes = "";
    private String msBtnNo = "";
    private Drawable mLogodrawable = null;
    private int mnLayoutResId = R.layout.popup_generic;


    public static int BTN_CLOSE = 1;
    public static int BTN_NO = 3;
    public static int BTN_YES = 4;


    public CustomDialog(Activity activity, String sMsg, String sBtnYes, String sBtnNo) {
        super(activity);
        mActivity = activity;
        msMsg = sMsg;
        msBtnYes = sBtnYes;
        msBtnNo = sBtnNo;
    }

    public void setListener(CustomDialogCompleteListener listener) {
        mListerner = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(mnLayoutResId);
        setCanceledOnTouchOutside(false);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);


        ivClose = (ImageView) findViewById(R.id.btn_close);
        mivIcon = (ImageView) findViewById(R.id.btn_icon);
        mtvMessage = (TextView) findViewById(R.id.tv_message);
        btnYes = (Button) findViewById(R.id.btn_yes);
        btnNo = (Button) findViewById(R.id.btn_no);

        btnYes.setText(msBtnYes);
        btnNo.setText(msBtnNo);
        mtvMessage.setText(msMsg);


        if (mLogodrawable != null) mivIcon.setImageDrawable(mLogodrawable);

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListerner)
                    mListerner.onComplete(BTN_CLOSE);
                dismiss();
            }
        });

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListerner)
                    mListerner.onComplete(BTN_YES);
                dismiss();
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListerner)
                    mListerner.onComplete(BTN_NO);
                dismiss();
            }
        });
    }

    public void setImage(int nId) {
        if (null == mivIcon)
            mLogodrawable = mActivity.getResources().getDrawable(nId);
        else
            mivIcon.setImageResource(R.drawable.ic_menu_gallery);
    }

    public void setImage(Drawable drawable) {
        if (null == mivIcon)
            mLogodrawable = drawable;
        else
            mivIcon.setImageDrawable(drawable);
    }

    public void setPosition(int y, int x) {

        LayoutParams params = new LayoutParams();
        params.y = y;
        params.x = x;
        params.gravity = Gravity.CENTER;
        getWindow().setAttributes(params);

    }
}
