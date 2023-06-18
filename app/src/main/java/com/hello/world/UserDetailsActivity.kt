package com.hello.world

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.json.JSONObject

class UserDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        //parse the json
        val userDetailsString = assets.open("userDetails.json").bufferedReader().readText()
        val userDetailsJson = JSONObject(userDetailsString)

        val userName = userDetailsJson.getString("user_name")
        val userDesignation = userDetailsJson.getString("user_designation")
        val company = userDetailsJson.getString("company")
        val mobileNo = userDetailsJson.getLong("mobile_no")
        val status = userDetailsJson.getBoolean("is_active")

        //views
        val userNameTxt = findViewById<TextView>(R.id.user_name_txt)
        val userDesignationTxt = findViewById<TextView>(R.id.user_designation_txt)
        val companyTxt = findViewById<TextView>(R.id.user_company_txt)
        val mobileNoTxt = findViewById<TextView>(R.id.user_mobile_no_txt)

        userNameTxt.text = userName
        userDesignationTxt.text = userDesignation
        companyTxt.text = company
        mobileNoTxt.text = mobileNo.toString()
    }
}