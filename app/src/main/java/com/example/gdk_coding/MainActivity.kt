package com.example.gdk_coding

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var Panjang:EditText
    private lateinit var Lebar:EditText
    private lateinit var Tinggi:EditText
    private lateinit var txtResult:TextView
    private lateinit var btnCalculate:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Panjang = findViewById(R.id.edt_P);
        Lebar = findViewById(R.id.edt_L);
        Tinggi = findViewById(R.id.edt_T);
        txtResult = findViewById(R.id.txt_R);
        btnCalculate = findViewById(R.id.Btn_Calculate);

        btnCalculate.setOnClickListener(this)

        if (savedInstanceState != null){
            val nil_result = savedInstanceState.getString(Result_State) as String
            txtResult.text = nil_result
        }

    }
    override fun onClick(v: View) {
        if (v.id == R.id.Btn_Calculate){
            val inputPanjang = Panjang.text.toString().trim()
            val inputLebar = Lebar.text.toString().trim()
            val inputTinggi = Tinggi.text.toString().trim()

            var Empaty = false
            var no_Double =false

            if (inputPanjang.isEmpty()){
                Empaty = true
                Panjang.error = "This field cannot be empty"
            }
            if (inputLebar.isEmpty()){
                Empaty = true
                Lebar.error = "This field cannot be empty"
            }
            if (inputTinggi.isEmpty()){
                Empaty = true
                Tinggi.error = "This field cannot be empty"
            }

            val P = toDouble(inputPanjang)
            val L = toDouble(inputLebar)
            val T = toDouble(inputTinggi)

            if (P == null){
                no_Double = true
                Panjang.error = "This field must consist of a valid number"
            }
            if (L == null){
                no_Double = true
                Lebar.error = "This field must consist of a valid number"
            }
            if (T == null){
                no_Double = true
                Tinggi.error = "This field must consist of a valid number"
            }
            if (!Empaty && !no_Double){
                val volume = P as Double * L as Double * T as Double
                txtResult.text = volume.toString()
                //val move = Intent
            }

        }


    }

    private fun toDouble(str: String): Double? {
            return try {
                str.toDouble()
            }catch (e: NumberFormatException){
                null
            }
    }
    companion object{
        private const val Result_State = "State Result"
    }

    //menyimpan nilai lenskep
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(Result_State,txtResult.text.toString())
    }


}



