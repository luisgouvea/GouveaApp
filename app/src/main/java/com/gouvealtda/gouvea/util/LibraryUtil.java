package com.gouvealtda.gouvea.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.Primitives;
import com.gouvealtda.gouvea.model.ResponseAPIModel;

import java.lang.reflect.Type;

import retrofit2.Response;

public class LibraryUtil {

    /*
    Preferences KEY
     */
    public static final String LIST_PREPARE_ITENS_ORDER = "listprepareitensorder";

    public static <T> T parseObjectToOtherObject(Object responseAPI, Class<T> classToParse) {
        Gson gSerializer = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

        String json = gSerializer.toJson(responseAPI);

        Object object = gSerializer.fromJson(json, (Type) classToParse);

        return Primitives.wrap(classToParse).cast(object);
    }

    public static <T> T parseResponseAPI(Response<ResponseAPIModel> responseRetrofit, Class<T> classToParse) {
        if (responseRetrofit == null) {
            return Primitives.wrap(classToParse).cast(new ResponseAPIModel());
        }
        if (responseRetrofit.code() == 200) {
            ResponseAPIModel responseAPI = responseRetrofit.body();
            if (LibraryUtil.hasErrorResponseAPI(responseAPI) == false) {
                return LibraryUtil.parseObjectToOtherObject(responseAPI, classToParse);
            } else {
                return Primitives.wrap(classToParse).cast(responseAPI);
            }
        }
        return Primitives.wrap(classToParse).cast(new ResponseAPIModel());
    }

    public static Boolean hasErrorResponseAPI (ResponseAPIModel responseAPI) {
        if (responseAPI == null) {
            return true;
        }
        if (responseAPI.isHasError() == false && responseAPI.getResponseDetail() != null) {
            return false;
        }
        return true;
    }

    /*
     * IsTextFieldEmpty
     * Verify whether text field is empty or not
     **/
    public static boolean IsTextFieldEmpty(EditText editText) {

        String input = editText.getText().toString().trim();
        return input.length() == 0;

    }

    public static Boolean checkTypeResponseAPI (Object object) {
        if (object instanceof ResponseAPIModel) {
            return false;
        } else {
            return true;
        }
    }

    public static String objectToString(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    /*
     * SetPreference
     * Set new preference in shared preference
     * */
    public static void SetPreference(String name, String data, Activity activity){
        SharedPreferences sharedPreferences = activity.getSharedPreferences("Gouvea", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(name,data);
        editor.commit();

    }

    /*
     * GetPreference
     * Get preference in shared preference
     * */
    public static String GetPreference(String name, Activity activity){
        SharedPreferences sharedPreferences = activity.getSharedPreferences("Gouvea", activity.MODE_PRIVATE);

        return sharedPreferences.getString(name, null);
    }

    /*
     * RemovePreference
     * Remove preference in shared preference
     * */
    public static void RemovePreference(String name, Activity activity){
        SharedPreferences sharedPreferences = activity.getSharedPreferences("Gouvea", activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.remove(name);
        editor.commit();

    }

    public static boolean stringIsNull(String stringTarget) {
        if (stringTarget == null) {
            return true;
        }
        stringTarget = stringTarget.trim();
        if (stringTarget.equals("") || stringTarget.length() <= 0) {
            return true;
        }

        return false;
    }

    public static boolean doubleIsNull(double doubleTarget) {

        if (doubleTarget <= 0) {
            return true;
        }

        return false;
    }

    public static boolean intIsNull(int doubleTarget) {

        if (doubleTarget == 0) {
            return true;
        }

        return false;
    }

    public static void enableDisableView(View view, boolean enabled) {
        view.setEnabled(enabled);
        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;

            for (int idx = 0; idx < group.getChildCount(); idx++) {
                enableDisableView(group.getChildAt(idx), enabled);
            }
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}

