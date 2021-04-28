package com.example.a03_rechner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        // Übergebene Daten von MainActivity werden empfangen
        val uebergebeneDaten : ArrayList<String>? = intent.getStringArrayListExtra("daten")
        val save : Boolean = intent.getBooleanExtra("save", true)
        // Ausgabe des Verlaufs
        if (uebergebeneDaten != null) {
            for (i in uebergebeneDaten) {
                println(i)
                val text = TextView(this)
                text.textSize = 20f
                text.text = i
                view.addView(text)
            }
        }
        // Bei Click auf Back, Wechsel auf MainActivity
        back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("daten", uebergebeneDaten)
            intent.putExtra("save", save)
            startActivity(intent)
        }

    }
}