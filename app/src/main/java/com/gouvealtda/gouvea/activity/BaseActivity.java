package com.gouvealtda.gouvea.activity;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.gouvealtda.gouvea.util.LibraryUtil;

public class BaseActivity extends AppCompatActivity {

    private Activity mActivity;
    private Context mContext;

    //Loader custom
    private RelativeLayout relativeLayoutLoaderCustom;
    private View rootViewContent;
    //Loader custom


    public void initialInterfaceActivity() {
        initialActivity();
    }

    public void initialActivity() {
        mActivity = this;
        mContext = this;
    }

    public Activity getActivity() {
        return mActivity;
    }

    public Context getContext() {
        return mContext;
    }

    public void setInterfacesFindView() {
    }

    public void setHandlerInterface() {
    }


    public void initialLoaderCustomUtil(View rootViewContent, RelativeLayout relativeLayoutLoaderCustom) {
        this.rootViewContent = rootViewContent;
        this.relativeLayoutLoaderCustom = relativeLayoutLoaderCustom;
    }

    public void startLoaderCustom() {
        if (relativeLayoutLoaderCustom != null && rootViewContent != null) {
            if (relativeLayoutLoaderCustom.getVisibility() == View.GONE) {
                relativeLayoutLoaderCustom.setVisibility(View.VISIBLE);
                LibraryUtil.enableDisableView(rootViewContent, false);
            }
        } else {
            Log.i("LOADER_CUSTOM", "Chamar o metodo: initialLoaderCustomUtil()");
        }
    }

    public void stopLoaderCustom(Boolean success) {
        if (relativeLayoutLoaderCustom != null && rootViewContent != null) {
            if (relativeLayoutLoaderCustom.getVisibility() == View.VISIBLE) {
                if (!success) {
                    LibraryUtil.enableDisableView(rootViewContent, true);
                }
                relativeLayoutLoaderCustom.setVisibility(View.GONE);
            }
        } else {
            Log.i("LOADER_CUSTOM", "Chamar o metodo: initialLoaderCustomUtil()");
        }
    }
}
