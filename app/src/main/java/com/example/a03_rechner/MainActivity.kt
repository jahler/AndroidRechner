package com.example.a03_rechner

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Float.parseFloat

class MainActivity : AppCompatActivity() {
    var id: Int = 0
    var save: Boolean = true
    private var list = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Verlauf wird, wenn man von der zweiten Aktivität kommt, wieder in die lokale Variable
        // gespeichert und weiter gefüllt
        val uebergebeneDaten : ArrayList<String>? = intent.getStringArrayListExtra("daten")
        if (uebergebeneDaten != null) {
            list = uebergebeneDaten
        }
        // Auch der Boolean, der angibt ob der Verlauf gespeichert werden soll, wird wieder in die
        // lokale Variable gespeichert (damit sich der Wert, also true oder false, nicht ändert)
        val save2 : Boolean = intent.getBooleanExtra("save", true)
        save = save2
        switchVerlauf.isChecked = save

        // Wechsel auf die zweite Activity um Verlauf anzuzeigen
        verlauf.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("daten", list)
            intent.putExtra("save", save)
            startActivity(intent)
        }
        // Variable, die angibt ob Verlauf gespeichert werden soll
        switchVerlauf.setOnCheckedChangeListener { _, isChecked ->
            save = isChecked
        }
        // Check() aufrufen, wenn Operator verädert wird
        radioGroup.setOnCheckedChangeListener { _, _ ->
            id = radioGroup.checkedRadioButtonId
            check()
        }
        // Check() aufrufen, wenn Zahl eingegeben wird
        number1.addTextChangedListener {
            check()
        }
        number2.addTextChangedListener {
            check()
        }
    }
    // Funktion, die versucht die Eingabe des Benutzers auszuwerten
    private fun check () {
        try {
            // Unterscheidung zwischen den Operatoren, Berechnen des Ergebnisses und speichern im Verlauf (wenn erwünscht)
            when (findViewById<RadioButton>(id).text) {
                "+" -> {
                    val ergeb = parseFloat(number1.text.toString())+parseFloat(number2.text.toString())
                    erg.text = ergeb.toString()
                    if (save) {
                        list.add(number1.text.toString() + " + " + number2.text.toString() + " = " + ergeb.toString())
                    }
                }
                "-" -> {
                    val ergeb = parseFloat(number1.text.toString())-parseFloat(number2.text.toString())
                    erg.text = ergeb.toString()
                    if (save) {
                        list.add(number1.text.toString() + " - " + number2.text.toString() + " = " + ergeb.toString())
                    }
                }
                "*" -> {
                    val ergeb = parseFloat(number1.text.toString())*parseFloat(number2.text.toString())
                    erg.text = ergeb.toString()
                    if (save) {
                        list.add(number1.text.toString() + " * " + number2.text.toString() + " = " + ergeb.toString())
                    }
                }
                "/" -> {
                    val ergeb =parseFloat(number1.text.toString())/parseFloat(number2.text.toString())
                    erg.text = ergeb.toString()
                    if (save) {
                        list.add(number1.text.toString() + " / " + number2.text.toString() + " = " + ergeb.toString())
                    }
                }
            }
        } catch (e: Exception) {
            println("missing argument")
        }
    }
}
