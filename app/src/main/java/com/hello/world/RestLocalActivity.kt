package com.hello.world

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import org.json.JSONObject

class RestLocalActivity : AppCompatActivity() {

    val appBaseUrl = "http://192.168.1.2:8080/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rest_local)

        httpGetJSON("$appBaseUrl/feed/posts") { success, _, root, _ ->
            Log.d("localhost: get :", root.toString())
        }

        val usernameEdit = findViewById<EditText>(R.id.user_name_edit)
        val passwordEdit = findViewById<EditText>(R.id.password_edit)
        val signinBtn  = findViewById<Button>(R.id.btn_sign_in)

        signinBtn.setOnClickListener {
            val jsonObject = JSONObject()
            jsonObject.put("title", usernameEdit.text.toString())
            jsonObject.put("content", passwordEdit.text.toString())
            httpPostJSON("$appBaseUrl/feed/post", jsonObject) {success, _, root, _ ->
                Log.d("localhost: post :", root.toString())
            }
        }
    }
}