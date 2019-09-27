package com.saucefan.stuff.kierantration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.annotation.MainThread
import kotlinx.android.synthetic.main.activity_entry_point.*
import android.content.Intent
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class entryPoint : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry_point)

        et_columns.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                popText(s.toString())
            }
        })
        et_rows.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                popText(s.toString())
            }
        })



        btn_enter.setOnClickListener{
            sanitizeEntries()


        }
    }

fun popText(string:String){
    Toast.makeText(this, string,Toast.LENGTH_SHORT).show()
}
    fun sanitizeEntries() {
        val rows = et_rows.text.toString().toInt()
        val columns =et_columns.text.toString().toInt()

        if (rows <= 0 || columns <=0){
            Toast.makeText(this,"Sorry we can't make a board without a real number of rows and columns! Try again!",Toast.LENGTH_SHORT).show()
        }
        else {
            val i = Intent(this, MainActivity::class.java)
            i.putExtra("ourRows", rows)
            i.putExtra("ourColumns", columns)
            startActivity(i)
        }
    }
}
