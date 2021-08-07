package com.login_signup_screendesign_demo;

import com.login_signup_screendesign_demo.R;

import android.*;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
	private static FragmentManager fragmentManager;
	final String ROOT_URL = "http://192.168.43.1/sj/";
	User_Info info;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);



		fragmentManager = getSupportFragmentManager();

		// If savedinstnacestate is null then replace login fragment
		if(SaveSharedPreference.getUserName(getApplicationContext()).length() == 0) {
			if (savedInstanceState == null) {
				setContentView(R.layout.activity_main);
				replaceLoginFragment();
			}

		}
		else {

			setContentView(R.layout.activity_main2);

			info = new User_Info();

			info.setUser_id(SaveSharedPreference.getPrefUserId(getApplicationContext()));
			info.setAns_given(SaveSharedPreference.getPrefUserAns(getApplicationContext()));
			info.setQues_asked(SaveSharedPreference.getPrefUserQues(getApplicationContext()));
			info.setBook_marks(SaveSharedPreference.getPrefUserBm(getApplicationContext()));
			info.setFollower(SaveSharedPreference.getPrefUserFoll(getApplicationContext()));
			info.setUpvote(SaveSharedPreference.getPrefUserUp(getApplicationContext()));

			info.setName(SaveSharedPreference.getPrefUserName(getApplicationContext()));
			Log.d("sab","inside M1 "+info.getName());
			info.setEmail_id(SaveSharedPreference.getUserName(getApplicationContext()));
//			info.setImageURL(SaveSharedPreference.getPrefImageUrl(getApplicationContext()));
			info.setAddress(SaveSharedPreference.getPrefAddr(getApplicationContext()));
			info.setPassword(SaveSharedPreference.getPrefPasswd(getApplicationContext()));
			info.setTagLine(SaveSharedPreference.getPrefTagLine(getApplicationContext()));

			Intent intent = new Intent(MainActivity.this,MainActivity2.class);
			intent.putExtra("Object",info);
			startActivity(intent);
			finish();

//			String emailid = SaveSharedPreference.getUserName(getApplicationContext());
//			Retrofit retrofit = new Retrofit.Builder().baseUrl(ROOT_URL).addConverterFactory(GsonConverterFactory.create()).build();
//			APIClient apiClient = retrofit.create(APIClient.class);
//
//			Call<List<User_Info>> call = apiClient.getUser_Info(emailid);
//
//			call.enqueue(new Callback<List<User_Info>>() {
//				@Override
//				public void onResponse(Call<List<User_Info>> call, Response<List<User_Info>> response) {
//					if(response.isSuccessful()) {
//						Log.d("PWD", "2");
//						List<User_Info> user_infos = response.body();
//						User_Info user_info = user_infos.get(0);
//
//					}
//				}
//
//				@Override
//				public void onFailure(Call<List<User_Info>> call, Throwable t) {
//					Log.d("PWD",""+t.getMessage());
//					t.printStackTrace();
//				}
//			});
		}

		// On close icon click finish activity
		findViewById(R.id.close_activity).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						finish();

					}
				});

	}

	// Replace Login Fragment with animation
	protected void replaceLoginFragment() {
		fragmentManager
				.beginTransaction()
				.setCustomAnimations(R.anim.left_enter, R.anim.right_out)
				.replace(R.id.frameContainer, new Login_Fragment(),
						Utils.Login_Fragment).commit();
	}

	@Override
	public void onBackPressed() {

		// Find the tag of signup and forgot password fragment
		Fragment SignUp_Fragment = fragmentManager
				.findFragmentByTag(Utils.SignUp_Fragment);
		Fragment ForgotPassword_Fragment = fragmentManager
				.findFragmentByTag(Utils.ForgotPassword_Fragment);

		// Check if both are null or not
		// If both are not null then replace login fragment else do backpressed
		// task

		if (SignUp_Fragment != null)
			replaceLoginFragment();
		else if (ForgotPassword_Fragment != null)
			replaceLoginFragment();
		else
			finish();
	}
}
