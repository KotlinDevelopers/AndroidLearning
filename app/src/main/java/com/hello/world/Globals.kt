package com.hello.world

import android.app.Activity
import android.util.Log
import androidx.fragment.app.Fragment
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import cz.msebera.android.httpclient.Header
import cz.msebera.android.httpclient.entity.StringEntity
import org.json.JSONArray
import org.json.JSONObject

fun Activity.httpGetJSON(url: String, responder: (Boolean, Int, JSONObject?, JSONArray?) -> Unit) {
    AsyncHttpClient().let {
        it.setMaxRetriesAndTimeout(2, 0)
        it.get(this, url, object : JsonHttpResponseHandler() {

            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                response: JSONArray?
            ) {
                responder(true, statusCode, null, response)
            }

            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                response: JSONObject?
            ) {
                responder(true, statusCode, response, null)
            }

            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseString: String?
            ) {
                responder(true, statusCode, null, null)
            }

            override fun onFailure(
                statusCode: Int, headers: Array<out Header>?, responseString: String?,
                throwable: Throwable?
            ) {
                Log.d("WHILOGS", "HTTP Error: Code: $statusCode, Response: $responseString")
                Log.e("WHILOGS", "HTTP Error", throwable)
                responder(false, statusCode, null, null)
            }

            override fun onFailure(
                statusCode: Int, headers: Array<out Header>?, throwable: Throwable?,
                errorResponse: JSONArray?
            ) {
                Log.d("WHILOGS", "HTTP Error: Code: $statusCode, Response: $errorResponse")
                Log.e("WHILOGS", "HTTP Error", throwable)
                responder(false, statusCode, null, null)
            }

            override fun onFailure(
                statusCode: Int, headers: Array<out Header>?, throwable: Throwable?,
                errorResponse: JSONObject?
            ) {
                Log.d("WHILOGS", "HTTP Error: Code: $statusCode, Response: $errorResponse")
                Log.e("WHILOGS", "HTTP Error", throwable)
                responder(false, statusCode, null, null)
            }
        })
    }
}

fun Activity.httpPostJSON(
    url: String, jsonData: JSONObject,
    responder: (Boolean, Int, JSONObject?, JSONArray?) -> Unit
) {
    AsyncHttpClient().let {
        it.setMaxRetriesAndTimeout(2, 0)
        it.post(this, url, StringEntity(jsonData.toString()),
            "application/json", object : JsonHttpResponseHandler() {

                override fun onSuccess(
                    statusCode: Int, headers: Array<out Header>?,
                    response: JSONArray?
                ) {
                    responder(true, statusCode, null, response)
                }

                override fun onSuccess(
                    statusCode: Int, headers: Array<out Header>?,
                    response: JSONObject?
                ) {
                    responder(true, statusCode, response, null)
                }

                override fun onSuccess(
                    statusCode: Int, headers: Array<out Header>?,
                    responseString: String?
                ) {
                    responder(true, statusCode, null, null)
                }

                override fun onFailure(
                    statusCode: Int, headers: Array<out Header>?, responseString: String?,
                    throwable: Throwable?
                ) {
                    Log.d("WHILOGS", "HTTP Error: Code: $statusCode, Response: $responseString")
                    Log.e("WHILOGS", "HTTP Error", throwable)
                    responder(false, statusCode, null, null)
                }

                override fun onFailure(
                    statusCode: Int, headers: Array<out Header>?,
                    throwable: Throwable?, errorResponse: JSONArray?
                ) {
                    Log.d("WHILOGS", "HTTP Error: Code: $statusCode, Response: $errorResponse")
                    Log.e("WHILOGS", "HTTP Error", throwable)
                    responder(false, statusCode, null, null)
                }

                override fun onFailure(
                    statusCode: Int, headers: Array<out Header>?, throwable: Throwable?,
                    errorResponse: JSONObject?
                ) {
                    Log.d("WHILOGS", "HTTP Error: Code: $statusCode, Response: $errorResponse")
                    Log.e("WHILOGS", "HTTP Error", throwable)
                    responder(false, statusCode, null, null)
                }
            })
    }
}