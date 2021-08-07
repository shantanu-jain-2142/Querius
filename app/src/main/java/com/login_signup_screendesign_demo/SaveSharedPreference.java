package com.login_signup_screendesign_demo;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;

import static android.R.attr.bitmap;

/**
 * Created by shantanu on 6/10/17.
 */

public class SaveSharedPreference {

  static final String PREF_EMAIL_ID= "emailid";
    static final String PREF_USER_NAME= "username";
    static final String PREF_TAG_LINE= "tagline";
    static final String PREF_IMAGE_URL= "imageurl";
    static final String PREF_PASSWD= "password";
    static final String PREF_ADDR= "address";

    static final String PREF_USER_ID = "userid";
    static final String PREF_USER_ANS = "user_ans";
    static final String PREF_USER_QUES = "user_ques";
    static final String PREF_USER_BM = "user_bm";
    static final String PREF_USER_FOLL = "user_foll";
    static final String PREF_USER_UP = "user_up";




    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setPrefEmailId(Context ctx, String emailid)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_EMAIL_ID, emailid);
        editor.apply();
    }


    public static void setUserName(Context ctx, String username)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        Log.d("sab","Inside setusername "+ctx.toString());
        editor.putString(PREF_USER_NAME, username);
        editor.apply();
    }

    public static void setPrefTagLine(Context ctx, String tag)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_TAG_LINE, tag);
        editor.apply();
    }

    public static void setPrefImageUrl(Context ctx, Bitmap iurl)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        iurl.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte imgByte[] = byteArrayOutputStream.toByteArray();
        String url = Base64.encodeToString(imgByte,Base64.DEFAULT);
        editor.putString(PREF_IMAGE_URL,url);
        editor.apply();
    }

    public static void setPrefAddr(Context ctx, String addr)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_ADDR, addr);
        editor.apply();
    }

    public static void setPrefPasswd(Context ctx, String pwd)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_PASSWD, pwd);
        editor.apply();
    }
    public static void setPrefUserId(Context ctx, int userid)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putInt(PREF_USER_ID, userid);
        editor.apply();
    }
    public static void setPrefUserAns(Context ctx, int userans)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putInt(PREF_USER_ANS, userans);
        editor.apply();
    }
    public static void setPrefUserQues(Context ctx, int userques)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putInt(PREF_USER_QUES, userques);
        editor.apply();
    }
    public static void setPrefUserBm(Context ctx, int bm)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putInt(PREF_USER_BM, bm);
        editor.apply();
    }
    public static void setPrefUserUp(Context ctx, int up)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putInt(PREF_USER_UP, up);
        editor.apply();
    }
    public static void setPrefUserFoll(Context ctx, int foll)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putInt(PREF_USER_FOLL, foll);
        editor.apply();
    }


    public static String getUserName(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_EMAIL_ID, "");
    }


    public static void clearUserName(Context ctx)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        Log.d("Kaisa Hai",""+ctx);
//        editor.putString(PREF_EMAIL_ID,"");
        editor.clear(); //clear all stored data
        editor.apply();
    }


    public static String getPrefUserName(Context ctx) {
        Log.d("sab","Inside getUsername "+ctx.toString());
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }

    public static String getPrefTagLine(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_TAG_LINE, "");
    }

    public static Bitmap getPrefImageUrl(Context ctx) {

        String url = getSharedPreferences(ctx).getString(PREF_IMAGE_URL, "");
        byte [] encodeByte=Base64.decode(url,Base64.DEFAULT);
        Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        return bitmap;
    }

    public static String getPrefPasswd(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_PASSWD, "");
    }

    public static String getPrefAddr(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_ADDR, "");
    }

    public static int getPrefUserId(Context ctx) {
        return getSharedPreferences(ctx).getInt(PREF_USER_ID, -1);
    }

    public static int getPrefUserAns(Context ctx) {
        return getSharedPreferences(ctx).getInt(PREF_USER_ANS, -1);
    }

    public static int getPrefUserQues(Context ctx) {
        return getSharedPreferences(ctx).getInt(PREF_USER_QUES, -1);
    }

    public static int getPrefUserBm(Context ctx) {
        return getSharedPreferences(ctx).getInt(PREF_USER_BM, -1);
    }

    public static int getPrefUserUp(Context ctx) {
        return getSharedPreferences(ctx).getInt(PREF_USER_UP, -1);
    }

    public static int getPrefUserFoll(Context ctx) {
        return getSharedPreferences(ctx).getInt(PREF_USER_FOLL, -1);
    }
}
