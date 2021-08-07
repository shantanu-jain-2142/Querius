package com.login_signup_screendesign_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import com.onegravity.rteditor.RTEditText;
import com.onegravity.rteditor.RTManager;
import com.onegravity.rteditor.RTToolbar;
import com.onegravity.rteditor.api.RTApi;
import com.onegravity.rteditor.api.RTMediaFactoryImpl;
import com.onegravity.rteditor.api.RTProxyImpl;

public class Main4Activity extends AppCompatActivity {

    TextView ques_txt,q_user,q_tag,q_top;
    String q_txt,q_name,q_tagline,q_topic;
    int qid,quid;
    private RTManager rtManager;
    private RTEditText rtEditText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.RTE_ThemeLight);
        setContentView(R.layout.activity_main4);

        ques_txt = (TextView) findViewById(R.id.ques);
        q_top = (TextView) findViewById(R.id.ques_title);
        q_user = (TextView) findViewById(R.id.tv_name);
        q_tag = (TextView) findViewById(R.id.tv_qual);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("MAIN4");
        q_txt = bundle.getString("QUESTION TEXT");
        q_name = bundle.getString("Q_NAME");
        q_tagline=bundle.getString("Q_TAGLINE");
        qid=bundle.getInt("QUESTION ID");
        quid=bundle.getInt("QUSER ID");
        q_topic=bundle.getString("TOPIC NAME");

        ques_txt.setText(q_txt);
        q_top.setText(q_topic);
        q_user.setText(q_name);
        q_tag.setText(q_tagline);


        rtEditText = (RTEditText) findViewById(R.id.rtext);
// create RTManager
        RTApi rtApi = new RTApi(this, new RTProxyImpl(this), new RTMediaFactoryImpl(this, true));
        rtManager = new RTManager(rtApi, savedInstanceState);
        rtManager.registerEditor(rtEditText, true);


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();

        ViewGroup toolbarContainer = (ViewGroup) findViewById(R.id.rte_toolbar_container);
        RTToolbar rtToolbar = (RTToolbar) findViewById(R.id.rte_toolbar);
        if (rtToolbar != null) {
            rtManager.registerToolbar(toolbarContainer, rtToolbar);
        }

        // register editor & set text
        rtEditText = (RTEditText) findViewById(R.id.rtext);

    }
}
