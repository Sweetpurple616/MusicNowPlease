package nl.zoedewaard.musicnowplease

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_showactivity.*
import kotlinx.android.synthetic.main.activity_showactivity.view.*
import org.json.JSONObject
import java.lang.Exception
import android.widget.ImageView


class ShowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showactivity)


        //get SharedPref data
        val sharedPreferences = getSharedPreferences("WhatCity", Context.MODE_PRIVATE)
        val yourCity = sharedPreferences.getString("saved_your_city", "Rotterdam")



        val cityText = findViewById<TextView>(R.id.inputCity)
        cityText.append(yourCity)
        getInternetData(yourCity)


//        val events = mutableListOf<Event>()
//        for (i in 0..5) {
//            events.add(Event("concert","morgen","20:00", "rock"))
//        }
//        recyclerView.apply {
//            layoutManager = LinearLayoutManager(this@ShowActivity)
//            adapter = EventsAdapter(events)
//        }


    }

    // Makes url, gets data and push it to view
    private fun getInternetData(userCity: String?) {


        val textView = findViewById<TextView>(R.id.inputText)
        val queue = Volley.newRequestQueue(this)
        val url = standardUrl.plus("=").plus(userCity)


        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                // textView.text = "Response: %s".format(response.toString())
                val embedded = response.getJSONObject("_embedded");
                val events = embedded.getJSONArray("events")

                val event = events.getJSONObject(0)

                val date = event.getJSONObject("dates")
                val startDate = date.getJSONObject("start")
                val classifications = event.getJSONArray("classifications")
                val eventClass = classifications.getJSONObject(0)
                val genre = eventClass.getJSONObject("genre")
                val subGenre = eventClass.getJSONObject("subGenre")
                val imageObj = event.getJSONArray("images")
                val firstImage = imageObj.getJSONObject(0)
                val imgUrl = firstImage.getString("url")

                val ImgView = findViewById<ImageView>(R.id.inputImage)

                Picasso.get().load(imgUrl).into(ImgView);

                val eventName = event.getString("name")
                val eventDate = startDate.getString("localDate")
                var eventTime = ""
                try {
                    eventTime = startDate.getString("localTime")
                } catch (e:Exception) {
                 eventTime = "Their is no starting time"
                     }
                val eventGenre = genre.getString("name")
                val eventSubGenre = subGenre.getString("name")



                textView.append("Name : " + eventName + "\n \n" + "Date : " + eventDate + "\n \n" + "Time : " + eventTime + "\n \n" + "Genre : " + eventGenre + " " + eventSubGenre)
            },
            Response.ErrorListener { error ->
                textView.text = "thats a error"
            }

        )
        queue.add(jsonObjectRequest)

    }
}

