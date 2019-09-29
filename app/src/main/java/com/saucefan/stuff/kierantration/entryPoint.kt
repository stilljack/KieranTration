package com.saucefan.stuff.kierantration

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_entry_point.*


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
          //      popText(s.toString())
                editSanitization(s)
            }
        })
        et_rows.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
           //     popText(s.toString())


                editSanitization(s)
            }
        })

        btn_enter.setOnClickListener{
            sanitizeEntries()


        }
    }


    // probably a more elegant way to do this but basically...
    // checks if there's a char sequence at all,
    // checks if the first character is a 0
    // if there's some digits beyond the zero to the right, it keeps them in the field
    // if zero would be the only number, it makes it a one instead
    fun editSanitization(s: CharSequence) {

        if (s.isNotBlank()) {
            if (s.substring(0, 1).contains("0") && s.length >= 2) {
                if (et_rows.text.isNotEmpty()) {
                    et_rows.setText(et_rows.text.substring(1, et_rows.text.length))
                    return
                }
            } else if (s.toString() == "0") {
                et_rows.setText("")
            }
        }
    }


    fun popText(string: String) {

        Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
    }

    fun sanitizeEntries() {
        if (et_rows.text.isNotEmpty() && et_columns.text.isNotEmpty()) {
            val rows = et_rows.text.toString()?.toInt()
            val columns = et_columns.text.toString()?.toInt()
            if (rows >= 1 || columns >= 1) {
                val i = Intent(this, MainActivity::class.java)
                i.putExtra("ourRows", rows)
                i.putExtra("ourColumns", columns)
                startActivity(i)
            }
        } else {
            Toast.makeText(
                this,
                "Sorry we can't make a board without a real number of rows and columns! Try again!",
                Toast.LENGTH_SHORT
            ).show()

        }
    }

}
