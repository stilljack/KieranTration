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
        var rows = et_rows.editableText
        var columns =et_rows.editableText
        val i = Intent(this, MainActivity::class.java)
        i.putExtra("ourRows", rows)
        i.putExtra("ourNumbers", columns)
        startActivity(i)
    }
}
