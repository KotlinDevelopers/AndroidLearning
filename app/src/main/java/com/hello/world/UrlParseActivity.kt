package com.hello.world

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.hello.world.models.Post
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.JsonHttpResponseHandler
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL

class UrlParseActivity : AppCompatActivity() {

    //Global Variables, List
    private var postList= mutableListOf<Post>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_url_parse)
        val postListView = findViewById<ListView>(R.id.postsListView)
        val postAdapter = PostAdapter()
        postListView.adapter = postAdapter

        getJsonFromUrl()
        showImage()
/*
        Thread {
            val url = URL("https://jsonplaceholder.typicode.com/posts")
            val urlconnection = url.openConnection()
            val inputstrm = urlconnection.getInputStream()
            val bufferreader = inputstrm.bufferedReader()
            val readtext = bufferreader.readText()
            val postArray = JSONArray(readtext)
            repeat(postArray.length()) { index->
                val postObject = postArray.getJSONObject(index)
                val post = Post(
                    postObject.getInt("userId"),
                    postObject.getInt("id"),
                    postObject.getString("title"),
                    postObject.getString("body")
                )
                postList.add(post)
            }
            Log.d( "onCreate:  ", "Started")

            repeat(postList.size) {
                Log.d( "UserID: ", postList[it].title)
            }

            runOnUiThread {
                postAdapter.notifyDataSetChanged()
            }

        }.start()
*/

        //val postListView = findViewById<ListView>(R.id.postsListView)
       // val postAdapter = PostAdapter()
       // postListView.adapter = postAdapter

    }

    private fun showImage() {
        val sampleImage = findViewById<ImageView>(R.id.image_sample)
        val imageLoader = ImageLoader.getInstance().apply {
            init(ImageLoaderConfiguration.Builder(this@UrlParseActivity).build())
        }
        imageLoader.displayImage("https://asia.olympus-imaging.com/content/000107507.jpg", sampleImage)
    }

    private fun getJsonFromUrl() {
        val client = AsyncHttpClient()
        client.get("https://jsonplaceholder.typicode.com/posts", object: JsonHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                response: JSONObject?
            ) {
                super.onSuccess(statusCode, headers, response)
            }

            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                response: JSONArray?
            ) {
                super.onSuccess(statusCode, headers, response)
                Log.d( "onSuccess: ", response.toString())
            }

            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseString: String?
            ) {
                super.onSuccess(statusCode, headers, responseString)
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                throwable: Throwable?,
                errorResponse: JSONObject?
            ) {
                super.onFailure(statusCode, headers, throwable, errorResponse)
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                throwable: Throwable?,
                errorResponse: JSONArray?
            ) {
                super.onFailure(statusCode, headers, throwable, errorResponse)
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseString: String?,
                throwable: Throwable?
            ) {
                super.onFailure(statusCode, headers, responseString, throwable)
            }
        })
    }

    private fun httpGetJSON(url: String, responder:(Boolean, Int, JSONObject?, JSONArray?) -> Unit) {
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

    inner class PostAdapter: BaseAdapter() {
        override fun getCount() = postList.size
        // adapter's size

        override fun getItem(position: Int) = position

        override fun getItemId(position: Int) = position.toLong()

        //set the view
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
            var convertView: View? = convertView
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.view_post_title, parent, false)
            }
            val titleTxt = convertView?.findViewById<TextView>(R.id.view_post_title_txt)
            titleTxt?.text = postList[position].title
            return convertView
        }

    }


}