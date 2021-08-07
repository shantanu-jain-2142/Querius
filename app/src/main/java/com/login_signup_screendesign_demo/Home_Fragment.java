package com.login_signup_screendesign_demo;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home_Fragment extends Fragment {

    //    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    User_Info info;


    public Home_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.slider_tab, container, false);
//        toolbar = (Toolbar) v.findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


        info = (User_Info) getArguments().getSerializable("Com_object");
        Log.d("hello", "Info is inside HOME FRAGMENT:" + info);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        viewPager = (ViewPager) v.findViewById(R.id.viewpager);
//        setupViewPager(viewPager);
//
//        tabLayout = (TabLayout) v.findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(viewPager);
//
////        Intent i = getActivity().getIntent();
////        info = (User_Info) i.getSerializableExtra("Com_object");
//
//
//
//
//        FragmentManager fragmentManager = getChildFragmentManager();
//        fragmentManager.beginTransaction().replace().commit();


//
//
//
//        return v;
//    }
//
//
//    private void setupViewPager(ViewPager viewPager) {
//        home_screen_fragment fragment = new home_screen_fragment();
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("Com_object",info);
//        fragment.setArguments(bundle);
//
//        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
//        adapter.addFragment(fragment, "                HOME                ");
////        adapter.addFragment(new Two(), "TWO");
////        adapter.addFragment(new Three(), "THREE");
//        viewPager.setAdapter(adapter);
//    }

        return v;

    }
}



//class ViewPagerAdapter extends FragmentPagerAdapter {
//    private final List<Fragment> mFragmentList = new ArrayList<>();
//    private final List<String> mFragmentTitleList = new ArrayList<>();
//
//    public ViewPagerAdapter(FragmentManager manager) {
//        super(manager);
//    }
//
//    @Override
//    public Fragment getItem(int position) {
//        return (mFragmentList.get(position));
//    }
//
//    @Override
//    public int getCount() {
//        return mFragmentList.size();
//    }
//
//    public void addFragment(Fragment fragment, String title) {
//        mFragmentList.add(fragment);
//        mFragmentTitleList.add(title);
//    }
//
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return mFragmentTitleList.get(position);
//    }
//}
