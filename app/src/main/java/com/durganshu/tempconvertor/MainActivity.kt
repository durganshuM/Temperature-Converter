package com.durganshu.tempconvertor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var convertedTemp: Float? = 0.0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tempET = findViewById<EditText>(R.id.tempET)
        val convertFromATV = findViewById<AutoCompleteTextView>(R.id.convertFromATV)
        val convertToATV = findViewById<AutoCompleteTextView>(R.id.convertToATV)
        val convertBTN = findViewById<Button>(R.id.convertBTN)
        val convertTempTV = findViewById<TextView>(R.id.convertTempTV)
        val temperatureUnits = resources.getStringArray(R.array.TemperatureUnits)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, temperatureUnits)
        var fromTempUnit: String? = null
        var toTempUnit: String? = null
        convertFromATV.setAdapter(adapter)
        convertToATV.setAdapter(adapter)

        convertFromATV.setOnItemClickListener { adapterView, _, i, _ ->
            fromTempUnit = adapterView.getItemAtPosition(i).toString()
        }

        convertToATV.setOnItemClickListener { adapterView, _, i, _ ->
            toTempUnit = adapterView.getItemAtPosition(i).toString()
        }

        convertBTN.setOnClickListener {
            if(tempET.text.toString().isNotEmpty()){
                val temp = (tempET.text.toString()).toFloat()
                if (fromTempUnit!! == toTempUnit!!){
                    convertTempTV.text = "Converted Temperature: "
                    Toast.makeText(this,"Cannot Convert to Same Unit or NULL",Toast.LENGTH_SHORT).show()

                }
                else{
                    convertTempTV.text = "Converted Temperature: "
                    val convertedTemperature = convertTempTV.text.toString() + tempConverter(fromTempUnit,toTempUnit,temp).toString()

                    convertTempTV.text = convertedTemperature
                }

            }
            else{
                convertTempTV.text = "Converted Temperature: "
                Toast.makeText(this,"Please Enter a Temperature",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun tempConverter(fromTempUnit: String?, toTempUnit: String?, temp: Float): Float?{
        if(fromTempUnit == "Celsius" && toTempUnit == "Fahrenheit"){
            convertedTemp = ((temp * 9) / 5) + 32
        }

        else if(fromTempUnit == "Celsius" && toTempUnit == "Kelvin"){
            convertedTemp = temp + 273.15F
        }

        else if(fromTempUnit == "Fahrenheit" && toTempUnit == "Celsius"){
            convertedTemp = (temp - 32) * 5 / 9
        }

        else if(fromTempUnit == "Fahrenheit" && toTempUnit == "Kelvin"){
            convertedTemp = (temp - 32) * 5 / 9 + 273.15F
        }

        else if(fromTempUnit == "Kelvin" && toTempUnit == "Celsius"){
            convertedTemp = temp - 273.15F
        }

        else if(fromTempUnit == "Kelvin" && toTempUnit == "Fahrenheit"){
            convertedTemp = (1.8F * (temp - 273.15F)) + 32F
        }

        else{
            Toast.makeText(this,"Please Select Appropriate Option",Toast.LENGTH_SHORT).show()
        }

        return convertedTemp
    }
}