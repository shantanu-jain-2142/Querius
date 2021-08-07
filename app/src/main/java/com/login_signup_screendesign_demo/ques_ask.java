package com.login_signup_screendesign_demo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class ques_ask extends Fragment {


    AutoCompleteTextView completeTextView,autoCompleteTextView;
//    Spinner spinner;

    List<myPojo> list;
    List<topicID> id_list;
    private static View v;
    int topic_id;
    List<getQuestions> list1;
    ArrayList<String> stringArrayList = new ArrayList<>();
    ArrayList<String> stringArrayList1 = new ArrayList<>();
    Button button;
    User_Info info;
    public static Animation shakeAnimation;
    public static RelativeLayout ques_ask_layout;
    TextView ques_asker;

    final String ROOT_URL = "http://192.168.43.1/sj/";
    final String TAG = "QUES";
    String getTitle,getQues;


    public ques_ask() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_ques_ask, container, false);

        completeTextView = (AutoCompleteTextView) v.findViewById(R.id.topic_suggest);
        autoCompleteTextView = (AutoCompleteTextView) v.findViewById(R.id.ask_ques);
        button = (Button) v.findViewById(R.id.ask_button);
        shakeAnimation = AnimationUtils.loadAnimation(ques_ask.this.getActivity(),
                R.anim.shake);
        ques_ask_layout = (RelativeLayout) v.findViewById(R.id.rel_layout);
        ques_asker = (TextView) v.findViewById(R.id.questionee);



        Intent i = getActivity().getIntent();
        info = (User_Info) i.getSerializableExtra("Com_object");
        if (info != null)
            Log.d(TAG, "At the beginning" + info.getUser_id());

//        spinner = (Spinner) v.findViewById(R.id.topic_spinner);

        ques_asker.setText(info.getName() + " asks");
        Log.d(TAG,"calling loadQues");
        loadQues();

        loadQuestions();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                button.setText("Asking...");
                getTitle = completeTextView.getText().toString();
                getQues = autoCompleteTextView.getText().toString();

                if(getTitle.equals("")|| getTitle.length()==0 || getQues.equals("") || getQues.length()==0){
                        ques_ask_layout.startAnimation(shakeAnimation);
                    new CustomToast().Show_Toast(ques_ask.this.getActivity(), view,
                            "Enter both credentials.");
                    button.setText("ASK");
                }

                else{

                    getTopic_ID();
                }
            }
        });


//        arrayList.add("Software Development");
//        arrayList.add("Computer Engineering");
//        arrayList.add("ENTC");
//        arrayList.add("IT");
//        arrayList.add("Artificial Intelligence");
//        arrayList.add("Machine Learning");

        return v;
    }


    private void loadQues(){
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(ROOT_URL).addConverterFactory(GsonConverterFactory.create()).build();
        quesAPI api = retrofit.create(quesAPI.class);

        Call<List<myPojo>> call = api.getTopics();

        call.enqueue(new Callback<List<myPojo>>() {
            @Override
            public void onResponse(Call<List<myPojo>> call, Response<List<myPojo>> response) {
                if(response.isSuccessful())
                {
                    list = response.body();
                    int i=0;
                    while(i<list.size()){
                        stringArrayList.add(list.get(i).getName());
                        Log.d(TAG,"List element is :"+list.get(i).getName());
                        i++;
                    }
//                    Log.d(TAG,"List element is :"+list.get(0));

//                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(ques_ask.this.getActivity(),R.layout.support_simple_spinner_dropdown_item,list.);
                    ArrayAdapter<String> stringArrayAdapter =  new ArrayAdapter<String>(ques_ask.this.getActivity(),R.layout.support_simple_spinner_dropdown_item,stringArrayList);
//        ArrayAdapter<String> arrayAdapter1 =  new ArrayAdapter<String>(ques_ask.this.getActivity(),R.layout.support_simple_spinner_dropdown_item,arrayList);



                    completeTextView.setAdapter(stringArrayAdapter);

//        spinner.setAdapter(arrayAdapter1);
                    completeTextView.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            completeTextView.showDropDown();
                            Log.d(TAG,"Adapter set");
                            return false;
                        }
                    });

//                    autoCompleteTextView.setOnTouchListener(new View.OnTouchListener() {
//                        @Override
//                        public boolean onTouch(View view, MotionEvent motionEvent) {
//                            autoCompleteTextView.showDropDown();
//                            return false;
//                        }

//                    });
                }
                else{
                    Toast.makeText(getContext(),"Some Error Occurred",Toast.LENGTH_SHORT).show();
                    Log.d(TAG,"No response");
                    button.setText("ASK");
                }
            }

            @Override
            public void onFailure(Call<List<myPojo>> call, Throwable t) {
                Toast.makeText(getContext(),"Check Your network",Toast.LENGTH_SHORT).show();
                Log.d(TAG,""+t.getMessage());
                button.setText("ASK");
            }
        });



    }


    private void loadQuestions(){
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(ROOT_URL).addConverterFactory(GsonConverterFactory.create()).build();
        getquesAPI quesAPI = retrofit.create(getquesAPI.class);

        Call<List<getQuestions>> call = quesAPI.getQues();

        call.enqueue(new Callback<List<getQuestions>>() {
            @Override
            public void onResponse(Call<List<getQuestions>> call, Response<List<getQuestions>> response) {
                if(response.isSuccessful()){
                    list1 = response.body();
                    int i=0;
                    while(i<list1.size()){
                        stringArrayList1.add(list1.get(i).getQues());
                        Log.d(TAG,"List element is :"+list1.get(i).getQues());
                        i++;
                    }

                    ArrayAdapter<String> stringArrayAdapter =  new ArrayAdapter<String>(ques_ask.this.getActivity(),R.layout.support_simple_spinner_dropdown_item,stringArrayList1);
                    autoCompleteTextView.setAdapter(stringArrayAdapter);

                    autoCompleteTextView.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            autoCompleteTextView.showDropDown();
                            return false;
                        }

                    });
                }
                else{
                    Toast.makeText(getContext(),"Some Error Occurred",Toast.LENGTH_SHORT).show();
                    Log.d(TAG,"No response in autocomplete");
                }
            }

            @Override
            public void onFailure(Call<List<getQuestions>> call, Throwable t) {
                Toast.makeText(getContext(),"Check Your network",Toast.LENGTH_SHORT).show();
                Log.d(TAG,"showing menu"+t.getMessage());
            }
        });


    }

    private void getTopic_ID(){


//
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(interceptor)
//                .retryOnConnectionFailure(true)
//                .connectTimeout(15, TimeUnit.SECONDS)
//                .build();


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        final Retrofit retrofit = new Retrofit.Builder().baseUrl(ROOT_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        getTopicID id = retrofit.create(getTopicID.class);

        Call<List<topicID>> call = id.getTopic_id(getTitle);

        call.enqueue(new Callback<List<topicID>>() {
            @Override
            public void onResponse(Call<List<topicID>> call, Response<List<topicID>> response) {
                if(response.isSuccessful()) {
                    id_list = response.body();
                    topic_id = id_list.get(0).getTid();
                    Log.d(TAG,"Topic ID is :"+topic_id);
                    insert_ques();
                }
                else{
                    Toast.makeText(getContext(),"Please Select A valid topic",Toast.LENGTH_SHORT).show();
                    button.setText("ASK");
                }
            }

            @Override
            public void onFailure(Call<List<topicID>> call, Throwable t) {
                Toast.makeText(getContext(),"Check Your network",Toast.LENGTH_SHORT).show();
                Log.d(TAG,"getting id "+t.getMessage());
                t.printStackTrace();
                button.setText("ASK");
            }
        });

    }



    private void insert_ques(){

        Log.d(TAG,"Inside insert_ques:"+topic_id);
        Log.d(TAG,"Inside insert_ques:"+info.getUser_id());
        Log.d(TAG,"Inside insert_ques:"+getQues);

        final Retrofit retrofit = new Retrofit.Builder().baseUrl(ROOT_URL).addConverterFactory(GsonConverterFactory.create()).build();
        RegisterQuesAPI quesAPI = retrofit.create(RegisterQuesAPI.class);

        Call<Integer> call = quesAPI.insertQues(info.getUser_id(),topic_id,getQues);

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                int value=-1;
                value=response.body();
                if(value==1){
                    ques_ask.this.getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            new CustomToast().Show_Toast(getActivity(), v,
                                    "Question Asked");
                            button.setText("ASK");
                        }
                    });

                    button.setText("ASK");
                    autoCompleteTextView.setText("");
                    completeTextView.setText("");
                }
                else{
                    ques_ask.this.getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            new CustomToast().Show_Toast(getActivity(), v,
                                    "Question has a a problem");
                            button.setText("ASK");
                        }
                    });
                }
            }


            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(getContext(),"Check Your network",Toast.LENGTH_SHORT).show();
                Log.d(TAG,"inserting"+t.getMessage());
                button.setText("ASK");
            }
        });
    }


}
