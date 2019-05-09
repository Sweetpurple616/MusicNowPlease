package nl.zoedewaard.musicnowplease

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Onclick note button to ShowActivity
        noteButton.setOnClickListener {
            startActivity(Intent(this, ShowActivity::class.java))
        }
        //Onclick cog button to settings
        settingsButton.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))     }
    }
}
