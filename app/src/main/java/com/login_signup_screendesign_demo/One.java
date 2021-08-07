package com.login_signup_screendesign_demo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ms.square.android.expandabletextview.ExpandableTextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class One extends Fragment {


    private ExpandableTextView expandableTextView;
    private ImageButton imageButton;
    private TextView textView,tv1,tv2;
    String longText="Ravionics started with an aspiration to establish itself as a knowledge-based technology company in the engineering realm. Ravionics is always conscious of the fact that though the quality of products in its portfolio is vital to sustain its business, success at scaling up its business and foraying into new market segments is contingent upon its capability to add value to each of its business deals.\n" +
            "\n" +
            "Ravionics delivers winning business outcomes through its deep industry experience and a 360 degree view of \"Business through Technology\" Ã¢Â\u0080Â\u0093 helping clients create successful and adaptive businesses. Through this effort, today, Ravionics stands with country-wide network of clients with over highly experience engineers and professionals striving to achieve growth through highest levels of customer-satisfaction.";

    public One() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.main_layout, container, false);

        expandableTextView = (ExpandableTextView) v.findViewById(R.id.expandable_text_view);
        textView = (TextView) v.findViewById(R.id.expandable_text);
        imageButton = (ImageButton) v.findViewById(R.id.expand_collapse);
        tv1 = (TextView) v.findViewById(R.id.tv_name);
        tv2 = (TextView) v.findViewById(R.id.tv_qual);
        tv1.setText("Shantanu Jain");
        tv2.setText("Android Developer");
        expandableTextView.setText(longText);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getContext(),"Hello",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(One.this.getActivity(),Main3Activity.class);
                startActivity(intent);
            }
        });



        return v;
    }

}
