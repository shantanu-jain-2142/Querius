package com.login_signup_screendesign_demo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by anuja on 9/10/17.
 */

public class getLocCred {

        @SerializedName("location")
        private String location;

        @SerializedName("startYear")
        private int syr;

        @SerializedName("endYear")
        private int eyr;
        public String getLocation() {
                return location;
        }
        public int getSyr() {
                return syr;
        }
        public int getEyr() {
                return eyr;
        }

    }
