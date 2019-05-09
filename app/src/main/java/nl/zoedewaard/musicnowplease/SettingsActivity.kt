package nl.zoedewaard.musicnowplease

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log.d
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import kotlinx.android.synthetic.main.activity_settings.*


class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)


        // Gets data from sharedPrefs
        val sharedPreferences = getSharedPreferences("WhatCity", Context.MODE_PRIVATE)
        val checkedBox = sharedPreferences.getString("saved_your_city", "none")

        shouldItBeChecked(checkedBox);


    }

    //Checked the lasts checked box with sharedPrefs data
    private fun shouldItBeChecked(checkedbox:String?) {
        if (checkedbox != "none") {

            if(checkedbox == "Amsterdam"){
                rGroup.check(R.id.rbAmsterdam)
            }
            if(checkedbox == "Den Haag"){
                rGroup.check(R.id.rbDenHaag)
            }
            if(checkedbox == "Rotterdam"){
                rGroup.check(R.id.rbRotterdam)
            }
            if(checkedbox == "Utrecht"){
                rGroup.check(R.id.rbUtrecht)
            }

        }

    }
    // Checks which button is clicked
    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.rbAmsterdam ->
                    if (checked) {
                        saveUserCity("Amsterdam")
                    }
                R.id.rbDenHaag ->
                    if (checked) {
                        saveUserCity("Den Haag")
                    }
                R.id.rbRotterdam ->
                    if (checked) {
                        saveUserCity("Rotterdam")
                    }
                R.id.rbUtrecht ->
                    if (checked) {
                        saveUserCity("Utrecht")
                    }
            }
        }
    }
    // Saves the checked box in SharedPref
    private fun saveUserCity(inputCity:String){
        val sharedPreferences = getSharedPreferences("WhatCity", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("saved_your_city", inputCity).apply();
        d("key", inputCity)
    }

}