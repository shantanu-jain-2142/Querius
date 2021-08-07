package com.login_signup_screendesign_demo;

import java.io.IOException;
import java.net.PasswordAuthentication;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.pm.PackageInstaller;
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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.*;
import javax.mail.internet.*;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForgotPassword_Fragment extends Fragment implements
		OnClickListener {
	private static View view;

	private static EditText emailId;
	private static TextView submit, back;
	private static LinearLayout forgot_password;
	final String ROOT_URL = "http://192.168.43.1/sj/";
	String pwd;
	public ForgotPassword_Fragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.forgotpassword_layout, container,
				false);
		initViews();
		setListeners();
		return view;
	}

	// Initialize the views
	private void initViews() {
		emailId = (EditText) view.findViewById(R.id.registered_emailid);
		submit = (TextView) view.findViewById(R.id.forgot_button);
		back = (TextView) view.findViewById(R.id.backToLoginBtn);
		forgot_password = (LinearLayout) view.findViewById(R.id.forgot_password);

		// Setting text selector over textviews
		XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
		try {
			ColorStateList csl = ColorStateList.createFromXml(getResources(),
					xrp);

			back.setTextColor(csl);
			submit.setTextColor(csl);

		} catch (Exception e) {
		}

	}

	// Set Listeners over buttons
	private void setListeners() {
		back.setOnClickListener(this);
		submit.setOnClickListener(this);
	}

	public void forgotUser(){
		final String getEmailId = emailId.getText().toString();
//		final String from = "shantanujainrko@gmail.com";

		Retrofit retrofit = new Retrofit.Builder().baseUrl(ROOT_URL).addConverterFactory(GsonConverterFactory.create()).build();
		APIClient apiClient = retrofit.create(APIClient.class);

		Call<List<User_Info>> call = apiClient.getUser_Info(getEmailId);
		call.enqueue(new Callback<List<User_Info>>() {
			@Override
			public void onResponse(Call<List<User_Info>> call, Response<List<User_Info>> response) {
				if(response.isSuccessful()) {
					Log.d("PWD","2");
					List<User_Info> user_infos = response.body();
					User_Info user_info = user_infos.get(0);
					pwd = user_info.getPassword();
					Log.d("PWD", "" + pwd);

					new Mailer().execute();
//					Log.d("Pwd", "Email Process Started");
//					Properties props = new Properties();
//					props.put("mail.smtp.host", "smtp.gmail.com");
//					props.put("mail.smtp.socketFactory.port", "465");
//					props.put("mail.smtp.socketFactory.class",
//							"javax.net.ssl.SSLSocketFactory");
//					props.put("mail.smtp.auth", "true");
//					props.put("mail.smtp.port", "465");
//
//					//get Session
//					Log.d("Pwd", "1");
//					Session session = Session.getDefaultInstance(props,
//							new javax.mail.Authenticator() {
//								protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
//									return new javax.mail.PasswordAuthentication(from, password);
//								}
//							});
//					//compose message
//					try {
//						Log.d("Pwd", "2");
//						MimeMessage message = new MimeMessage(session);
//						Log.d("Pwd", "3");
//						message.addRecipient(Message.RecipientType.TO, new InternetAddress(getEmailId));
//						Log.d("Pwd", "4");
//						message.setSubject("PASSWORD RESET");
//						Log.d("Pwd", "5");
//						message.setText("Your Password is " + pwd);
//						Log.d("Pwd", "6");
//						//send message
//						Transport.send(message);
////				System.out.println("message sent successfully");
//						Log.d("Pwd", "7");
////					ForgotPassword_Fragment.this.getActivity().runOnUiThread(new Runnable() {
////						@Override
////						public void run() {
//////							forgot_password.startAnimation(shakeAnimation);
////							new CustomToast().Show_Toast(getActivity(), view, "Password Sent");
//////						Toast.makeText(getActivity(),"Mail Sent",Toast.LENGTH_SHORT);
////							Log.d("Pwd","Message Sent");
////						}
////					});
//
//					} catch (MessagingException e) {
//						throw new RuntimeException(e);
//					}
				}

			}

			@Override
			public void onFailure(Call<List<User_Info>> call, Throwable t) {
				if(t instanceof IOException){
					Log.d("PWD","IOEXCEPTION");
				}
					Log.d("PWD",""+t.getMessage());
					t.printStackTrace();
			}
		});

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.backToLoginBtn:

			// Replace Login Fragment on Back Presses
			new MainActivity().replaceLoginFragment();
			break;

		case R.id.forgot_button:

			// Call Submit button task
			submitButtonTask();
			new MainActivity().replaceLoginFragment();
			break;

		}

	}

	private void submitButtonTask() {
//		String getEmailId = emailId.getText().toString();
		String getEmailId = emailId.getText().toString();
//		final String from = "shantanujainrko@gmail.com";
//		final String password = "l6s3s7s26";
//		// Pattern for email id validation
		Pattern p = Pattern.compile(Utils.regEx);

		// Match the pattern
		Matcher m = p.matcher(getEmailId);

		// First check if email id is not null else show error toast
		if (getEmailId.equals("") || getEmailId.length() == 0)

			new CustomToast().Show_Toast(getActivity(), view,
					"Please enter your Email Id.");

		// Check if email id is valid or not
		else if (!m.find())
			new CustomToast().Show_Toast(getActivity(), view,
					"Your Email Id is Invalid.");

		// Else submit email id and fetch passwod or do your stuff
		else{
			forgotUser();

//			return null;
//			new Mailer().execute();
		}
//			Toast.makeText(getActivity(), "Get Forgot Password.",
//					Toast.LENGTH_SHORT).show();
//		class Mailer{
//			public void send(final String from,final String password,String to,String sub,String msg){
//				//Get properties object
//				Properties props = new Properties();
//				props.put("mail.smtp.host", "smtp.gmail.com");
//				props.put("mail.smtp.socketFactory.port", "465");
//				props.put("mail.smtp.socketFactory.class",
//						"javax.net.ssl.SSLSocketFactory");
//				props.put("mail.smtp.auth", "true");
//				props.put("mail.smtp.port", "465");
//				//get Session
//				PackageInstaller.Session session = Session.getDefaultInstance(props,
//						new javax.mail.Authenticator() {
//							protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
//								return new PasswordAuthentication(from,password);
//							}
//						});
//				//compose message
//				try {
//					MimeMessage message = new MimeMessage(session);
//					message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
//					message.setSubject(sub);
//					message.setText(msg);
//					//send message
//					Transport.send(message);
//					System.out.println("message sent successfully");
//				} catch (MessagingException e) {throw new RuntimeException(e);}
//
//			}
//		}
//		class Hello{
//			public static void main(String[] args) {
//				//from,password,to,subject,message
//				Mailer.send("from-send@gmail.com","xxxxxx","to-send@gmail.com","hello javatpoint","How r u?");
//				//change from, password and to
//			}
//		}

	}

	class Mailer extends AsyncTask<Void,Void,Void>{

		String getEmailId = emailId.getText().toString();
		final String from = "developers.querius@gmail.com";
		final String password = "sdlproject";
//		String passwd;

		@Override
		protected Void doInBackground(Void... voids) {

//			try {
////				Class.forName("com.mysql.jdbc.Driver");
////				Connection con = DriverManager.getConnection("jdbc:mysql://192.168.1.5:3306/Quora", "root", "hello_world");
////				java.sql.Statement stmt = con.createStatement();
////				final String query = "SELECT * FROM User_Profile WHERE Email_ID=?";
////				final PreparedStatement ps = con.prepareStatement(query);
////				ps.setString(1, getEmailId);
////				final ResultSet rs = ps.executeQuery();
////				while(rs.next()) {
////
////					passwd = rs.getString("Password");
////					Log.d("Pwd",""+passwd);
////				}
////			new Log_in_failed();
////                    loginLayout.startAnimation(shakeAnimation);
////					ForgotPassword_Fragment.this.getActivity().runOnUiThread(new Runnable() {
////						@Override
////						public void run() {
//////							forgot_password.startAnimation(shakeAnimation);
////							new CustomToast().Show_Toast(getActivity(), view,
////									"Wrong Email_ID");
////						}
////					});
////				} else {
////					passwd = rs.getString("Password");
////					Log.d("Password",""+passwd);
////				}
//
//			} catch (Exception e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}

			Log.d("Pwd","Email Process Started");
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");

			//get Session
			Log.d("Pwd","1");
			Session session = Session.getDefaultInstance(props,
					new javax.mail.Authenticator() {
						protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
							return new javax.mail.PasswordAuthentication(from,password);
						}
					});
			//compose message
			try {
				Log.d("Pwd","2");
				MimeMessage message = new MimeMessage(session);
				Log.d("Pwd","3");
				message.addRecipient(Message.RecipientType.TO,new InternetAddress(getEmailId));
				Log.d("Pwd","4");
				message.setSubject("PASSWORD RESET");
				Log.d("Pwd","5");
				message.setText("Your Password is "+pwd);
				Log.d("Pwd","6");
				//send message
				Transport.send(message);
//				System.out.println("message sent successfully");
				Log.d("Pwd","7");
				if(ForgotPassword_Fragment.this.getActivity() != null) {
					ForgotPassword_Fragment.this.getActivity().runOnUiThread(new Runnable() {
						@Override
						public void run() {
							new CustomToast().Show_Toast(getActivity(), view, "Password Sent");
							Log.d("Pwd", "Message Sent");
						}
					});


				}

			} catch (MessagingException e) {throw new RuntimeException(e);}

			return null;
		}
	}

	@Override
	public void onStop() {

		super.onStop();
	}
}