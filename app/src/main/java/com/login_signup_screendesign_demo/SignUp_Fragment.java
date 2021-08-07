package com.login_signup_screendesign_demo;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SignUp_Fragment extends Fragment implements OnClickListener {
	private static View view;
	private static EditText fullName, emailId, location,tagline,
			password, confirmPassword;
	private static TextView login;
	private static Button signUpButton;
	private static CheckBox terms_conditions;
	final String ROOT_URL = "http://192.168.43.1/sj/";

	public SignUp_Fragment() {

	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.signup_layout, container, false);
		initViews();
		setListeners();
		return view;
	}

	// Initialize all views
	private void initViews() {
		fullName = (EditText) view.findViewById(R.id.fullName);
		emailId = (EditText) view.findViewById(R.id.userEmailId);
		tagline = (EditText) view.findViewById(R.id.tagLine);
		location = (EditText) view.findViewById(R.id.location);
		password = (EditText) view.findViewById(R.id.password);
		confirmPassword = (EditText) view.findViewById(R.id.confirmPassword);
		signUpButton = (Button) view.findViewById(R.id.signUpBtn);
		login = (TextView) view.findViewById(R.id.already_user);
		terms_conditions = (CheckBox) view.findViewById(R.id.terms_conditions);

		// Setting text selector over textviews
		XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
		try {
			ColorStateList csl = ColorStateList.createFromXml(getResources(),
					xrp);

			login.setTextColor(csl);
			terms_conditions.setTextColor(csl);
		} catch (Exception e) {
		}
	}



	// Set Listeners
	private void setListeners() {
		signUpButton.setOnClickListener(this);
		login.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.signUpBtn:

			// Call checkValidation method
			checkValidation();
			break;

		case R.id.already_user:

			// Replace login fragment
			new MainActivity().replaceLoginFragment();
			break;
		}

	}

	public void insertUser() {
//		final int[] verified = new int[1];
		Retrofit retrofit = new Retrofit.Builder().baseUrl(ROOT_URL).addConverterFactory(GsonConverterFactory.create()).build();
		RegisterAPI api = retrofit.create(RegisterAPI.class);

		Call<Integer> call = api.insertUser(fullName.getText().toString(),
				emailId.getText().toString(),
				location.getText().toString(),
                tagline.getText().toString(),
				password.getText().toString());

		call.enqueue(new Callback<Integer>() {
			@Override
			public void onResponse(Call<Integer> call, Response<Integer> response) {
				Log.d("retro", "1");
				int value = response.body();
//				if (SignUp_Fragment.this.getActivity() == null)
//					return;
				if(value==1) {
					SignUp_Fragment.this.getActivity().runOnUiThread(new Runnable() {
						@Override
						public void run() {
							new CustomToast().Show_Toast(getActivity(), view,
									"Account Created");
						}
					});
					new MainActivity().replaceLoginFragment();

				}
				else{
					SignUp_Fragment.this.getActivity().runOnUiThread(new Runnable() {
						@Override
						public void run() {
							new CustomToast().Show_Toast(getActivity(), view,
									"EmailID already exists");
						}
					});
				}

			}

			@Override
			public void onFailure(Call<Integer> call, Throwable t) {
				SignUp_Fragment.this.getActivity().runOnUiThread(new Runnable() {
					@Override
					public void run() {
						new CustomToast().Show_Toast(getActivity(), view,
								"Check your internet connection");
					}
				});
			}
		});
	}

//			@Override
//			public void onFailure(Call<Integer> call, Throwable t) {
//				Log.d("retro","2");
//				if(SignUp_Fragment.this.getActivity()==null)
//					return;
//				SignUp_Fragment.this.getActivity().runOnUiThread(new Runnable() {
//					@Override
//					public void run() {
//						new CustomToast().Show_Toast(getActivity(), view,
//								"Email_id Already Exists.");
//					}
//				});
//
//			}

//
//						 @Override
//						 public void onFailure(Call<Integer> call, Throwable t) {
//							 Log.d("retro","2");
//							 if(SignUp_Fragment.this.getActivity()==null)
//								 return;
//							 SignUp_Fragment.this.getActivity().runOnUiThread(new Runnable() {
//								 @Override
//								 public void run() {
//									 new CustomToast().Show_Toast(getActivity(), view,
//											 "Email_id Already Exists.");
//								 }
//							 });
//
//						 }
//						 });
//
			// Check Validation Method
	private void checkValidation() {

		// Get all edittext texts
		String getFullName = fullName.getText().toString();
		String getEmailId = emailId.getText().toString();
		String getLocation = location.getText().toString();
		String getPassword = password.getText().toString();
        String getTagline = tagline.getText().toString();
		String getConfirmPassword = confirmPassword.getText().toString();

		// Pattern match for email id
		Pattern p = Pattern.compile(Utils.regEx);
		Matcher m = p.matcher(getEmailId);

		// Check if all strings are null or not
		if (getFullName.equals("") || getFullName.length() == 0
				|| getEmailId.equals("") || getEmailId.length() == 0
				|| getLocation.equals("") || getLocation.length() == 0
                || getTagline.equals("") || getTagline.length() == 0
				|| getPassword.equals("") || getPassword.length() == 0
				|| getConfirmPassword.equals("")
				|| getConfirmPassword.length() == 0) {
			new CustomToast().Show_Toast(getActivity(), view,
					"All fields are required.");
		}

		// Check if email id valid or not
		else if (!m.find()) {
			new CustomToast().Show_Toast(getActivity(), view,
					"Your email-id is invalid");
		}

		// Check if both password should be equal
		else if (!getConfirmPassword.equals(getPassword)) {
			new CustomToast().Show_Toast(getActivity(), view,
					"Your password is incorrect.");

		}

		// Make sure user should check Terms and Conditions checkbox
		else if (!terms_conditions.isChecked()) {
			new CustomToast().Show_Toast(getActivity(), view,
					"Please Select Terms & Conditions.");

		}

		// Else do signup or do your stuff
		else {
//			Toast.makeText(getActivity(), "Do SignUp.", Toast.LENGTH_SHORT)
//					.show();
//				new New_Signup().execute();
				insertUser();
//			new MainActivity().replaceLoginFragment();
//				new MainActivity().replaceLoginFragment();
		}

	}

	private class New_Signup extends AsyncTask<Void,Void,Void>{

		String getFullName = fullName.getText().toString();
		String getEmailId = emailId.getText().toString();
		String getMobileNumber;
		String getLocation = location.getText().toString();
		String getPassword = password.getText().toString();
	//	String getConfirmPassword = confirmPassword.getText().toString();
		@Override
		protected Void doInBackground(Void... voids) {

			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://192.168.1.5:3306/Quora", "root", "hello_world");
				java.sql.Statement stmt = con.createStatement();
				final String query = "INSERT INTO User_Profile (User_ID, Name, Email_ID, Address, Mobile_No, Password) VALUES (NULL,?,?,?,?,?)";
				final PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1,getFullName);
				ps.setString(2,getEmailId);
				ps.setString(3,getLocation);
				ps.setString(4,getMobileNumber);
				ps.setString(5,getPassword);
				int affectedRows = ps.executeUpdate();


			} catch (Exception e) {
				e.printStackTrace();
			}


			SignUp_Fragment.this.getActivity().runOnUiThread(new Runnable() {
				@Override
				public void run() {
					new CustomToast().Show_Toast(getActivity(), view,
							"Account Created");
				}
			});
			return null;
		}
	}
}
