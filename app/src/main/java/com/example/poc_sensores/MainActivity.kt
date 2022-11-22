package com.example.poc_sensores

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), SensorEventListener{

    lateinit var mSensorManager: SensorManager
    lateinit var mAccelerometer: Sensor
    lateinit var giroscopio: Sensor
    lateinit var proximidade: Sensor
    lateinit var acelerometroTextView: TextView
    lateinit var giroscopioTextView: TextView
    lateinit var proximidadeTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mSensorManager = getSystemService(SENSOR_SERVICE) as SensorManager;

        acelerometroTextView = findViewById(R.id.acelerometro)
        giroscopioTextView = findViewById(R.id.proximidade)
        proximidadeTextView = findViewById(R.id.giroscopio)

        //Verifica se o sensor de temperatura existe
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null){
            mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }else{
            acelerometroTextView.text = "Sensor indisponível"
        }

        giroscopio = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        proximidade = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

    }

    override fun onResume() {
        super.onResume()
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL)
        mSensorManager.registerListener(this, giroscopio, SensorManager.SENSOR_DELAY_NORMAL)
        mSensorManager.registerListener(this, proximidade, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        mSensorManager.unregisterListener(this)
    }

    override fun onDestroy() {
        mSensorManager.unregisterListener(this)
        super.onDestroy()
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        if (p0?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            acelerometroTextView.text = p0.values[0].toString()
        }
        if (p0?.sensor?.type == Sensor.TYPE_GYROSCOPE) {
            giroscopioTextView.text = p0.values[0].toString()
        }
        if (p0?.sensor?.type == Sensor.TYPE_PROXIMITY) {
            proximidadeTextView.text = p0.values[0].toString()
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        return
    }
}