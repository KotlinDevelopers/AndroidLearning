package com.hello.world

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat

class ConstraintLayoutExampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint_layout_example)

        //accessing the components
        val searchEdiTxt = findViewById<EditText>(R.id.search_edit_txt)
        val searchBtn = findViewById<Button>(R.id.search_btn)
        val sampleImage = findViewById<ImageView>(R.id.image1)
        val searchedWordTxt = findViewById<TextView>(R.id.searched_word_txt)

        //listeners
        searchBtn.setOnClickListener {
            val searchedWord = searchEdiTxt.text.toString()
            searchedWordTxt.text = searchedWord
            searchedWordTxt.textSize = 64f
            sampleImage.setImageDrawable(ResourcesCompat.getDrawable(resources,
                R.drawable.banner_1, null))
            sampleImage.background = ResourcesCompat.getDrawable(resources,
                R.drawable.sample_banner, null)
        }
    }
}