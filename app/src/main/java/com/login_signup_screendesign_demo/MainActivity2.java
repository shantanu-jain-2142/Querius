package com.login_signup_screendesign_demo;

import android.app.Fragment;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.io.Serializable;

public class MainActivity2 extends AppCompatActivity implements MenuItem.OnMenuItemClickListener{

//    private TextView mTextMessage;

    User_Info userInfo;
    MenuItem menuItem;

    BottomNavigationItemView home,notify,question,profile;

    ImageView iview,search_view;
    Context context;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:



//                    DrawableCompat.setTint(home.getForeground(), ContextCompat.getColor(getApplicationContext(),R.color.white));
//                    mTextMessage.setText(R.string.title_home);
                    FragmentManager fragmentManager1 = getSupportFragmentManager();
//                    getIntent().putExtra("Com_object",userInfo);
                    home_screen_fragment fragment123 = new home_screen_fragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("Com_object",userInfo);
                    fragment123.setArguments(bundle);
                    fragmentManager1.beginTransaction().replace(R.id.content,fragment123).addToBackStack(null).commit();

                    return true;



//                    DrawableCompat.setTint(notify.getForeground(), ContextCompat.getColor(getApplicationContext(),R.color.white));
//                    mTextMessage.setText(R.string.title_notifications
                case R.id.navigation_profile:
//                    mTextMessage.setText(R.string.title_profile);


//                    DrawableCompat.setTint(profile.getForeground(), ContextCompat.getColor(getApplicationContext(),R.color.white));
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    getIntent().putExtra("Com_object",userInfo);
                    android.support.v4.app.Fragment fragment = new android.support.v4.app.Fragment();
                    fragmentManager.beginTransaction().replace(R.id.content,new Profile_Fragment()).commit();
                    return true;

                case  R.id.ask_ques:



//                    DrawableCompat.setTint(question.getForeground(), ContextCompat.getColor(getApplicationContext(),R.color.white));
                    FragmentManager fragmentManager2 = getSupportFragmentManager();
                    getIntent().putExtra("Com_object",userInfo);
                    fragmentManager2.beginTransaction().replace(R.id.content,new ques_ask()).commit();
                    return true;
            }
            return true;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

//
//        home = (BottomNavigationItemView) findViewById(R.id.navigation_home);
//        notify = (BottomNavigationItemView) findViewById(R.id.navigation_notifications);
//        profile = (BottomNavigationItemView) findViewById(R.id.navigation_profile);
//        question = (BottomNavigationItemView) findViewById(R.id.ask_ques);

        Intent intent = getIntent();
        userInfo = (User_Info) intent.getExtras().getSerializable("Object");
        Log.d("sab","inside M2"+userInfo.getName());
//        context = (Context) intent.getExtras().getSerializable("Context");



        FragmentManager fragmentManager2 = getSupportFragmentManager();
        home_screen_fragment fragment123 = new home_screen_fragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("Com_object",userInfo);
        fragment123.setArguments(bundle);
        fragmentManager2.beginTransaction().replace(R.id.content,fragment123).commit();
//        if(userInfo != null) {
//            Log.d("User_info", "" + userInfo.getName());
//        }
        iview = (ImageView) findViewById(R.id.toggle);
//        search_view = (ImageView) findViewById(R.id.search);

        iview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu dropDownMenu = new PopupMenu(getApplicationContext(), iview);
                dropDownMenu.getMenuInflater().inflate(R.menu.menu_drop_down, dropDownMenu.getMenu());
                dropDownMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        switch (menuItem.getItemId()){
                            case R.id.dropdown_menu1:
//                                SaveSharedPreference.setUserName(getApplicationContext(),"");
                                SaveSharedPreference.clearUserName(getApplicationContext());
                                Intent intent1 = new Intent(MainActivity2.this,MainActivity.class);
//                                Login_Fragment fragment = new Login_Fragment();
                                onDestroy();
                                startActivity(intent1);
                                return true;
                        }

//                        Toast.makeText(Profile_Fragment.this.getActivity(), "You have clicked " + menuItem.getTitle(), Toast.LENGTH_LONG).show();
                        return true;
                    }
                });
                dropDownMenu.show();
            }

        });



//        search_view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });



//        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        return false;
    }

    @Override
    public void onBackPressed() {
//
//        FragmentManager fm = getSupportFragmentManager();
//        fm.popBackStack();

        Intent menuIntent = new Intent();
        menuIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
//        super.onBackPressed();
    }


    //    @Override
//    public void doSomething(User_Info info) {
//        userInfo = info;
//        if(userInfo != null) {
//            Log.d("M2", "" + userInfo.getName());
//        }
//
//    }


}


//
//class myHelper{
//
//    Context context;
//    public myHelper(Context context) {
//        this.context=context;
//    }
//
//    public Context get_con(){
//        return context;
//    }
//
//    public void setContext(Context context) {
//        this.context = context;
//    }
//}
