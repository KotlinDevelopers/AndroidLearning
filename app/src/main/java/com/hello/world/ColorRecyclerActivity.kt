package com.hello.world

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.hello.world.models.ColorModel
import org.json.JSONArray

class ColorRecyclerActivity : AppCompatActivity() {
    val colorList = mutableListOf<ColorModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_recycler)

        //parse the json
        val colorJson = JSONArray(assets.open("colors.json").bufferedReader().readText())

        //convert into list using model
        repeat(colorJson.length()) { index->
            val color = colorJson.getJSONObject(index).getString("color")
            val value  = colorJson.getJSONObject(index).getString("value")
            val colorModel = ColorModel(color, value)
            colorList.add(colorModel)
            //colorList.add(ColorModel(colorJson.getJSONObject(index).getString("color"),
                //colorJson.getJSONObject(index).getString("value")))
        }
        Log.d("ColorListSize", colorList.size.toString())

        //method to create the adapter
       setColorAdapter(colorList)
    }

    private fun test() {

    }

    private fun setColorAdapter(colorList: MutableList<ColorModel>) {
        val colorRecyclerView = findViewById<RecyclerView>(R.id.colors_list_recucler_view)
        val colorAdapter = ColorAdapter()
        colorRecyclerView.adapter = colorAdapter
    }

    inner class ColorAdapter: RecyclerView.Adapter<ColorViewHolder>() {

        /**
         *  1. create the viewholder
         *  2. set the view, count, data
         *  3. set the views in the viewholder
         */
        //set the view
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
            return ColorViewHolder(layoutInflater.inflate(R.layout.view_color_text, parent,
                false), this@ColorRecyclerActivity)
        }

        // set the count
        override fun getItemCount(): Int {
            return colorList.size
        }

        //set the datas into the corresponding views
        override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
            holder.setData(colorList[position])
        }

    }

    //Viewholder
    class ColorViewHolder(rootView: View, val context: Context): RecyclerView.ViewHolder(rootView) {

        fun setData(colorModel: ColorModel) {
            textView.text = colorModel.colorName
            //textView.setBackgroundColor(ResourcesCompat.getColor(context.resources, R.color.purple_200, null))
            //textView.setBackgroundColor(colorModel.colorValue as Int)
        }

        val textView = rootView.findViewById<TextView>(R.id.view_color_txt)


    }


}