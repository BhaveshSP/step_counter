package com.bhaveshsp.stepcounter

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity(),SensorEventListener{
    private lateinit var sensorManager:SensorManager
    private lateinit var textView: TextView
    private var isRunning=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView=findViewById(R.id.displayStepText)
        sensorManager=getSystemService(Context.SENSOR_SERVICE) as SensorManager

    }
    fun startCounting(view: View){
        if (isRunning){
        val countSensor=sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
            if (countSensor!=null){
                sensorManager.registerListener(this,countSensor,SensorManager.SENSOR_DELAY_UI)
            }
            else{
                Toast.makeText(this, "Sensor not found", Toast.LENGTH_SHORT).show()
            }
        }
        else{
            sensorManager.unregisterListener(this)
        }
        isRunning=!isRunning
    }
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        TODO("Not yet implemented")
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (isRunning){
            textView.text=event!!.values[0].toString()
        }
    }

}