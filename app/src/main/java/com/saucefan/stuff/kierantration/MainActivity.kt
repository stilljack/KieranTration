package com.saucefan.stuff.kierantration

import android.graphics.BlendMode
import android.os.Bundle
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.size
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rows =intent.getIntExtra("ourRows", 4)
        val columns = intent.getIntExtra("ourColumns", 4)
       makeGrid(columns,rows)



    }




    fun makeGrid(maxColumns: Int, maxRows: Int) {
        gridView.columnCount =maxColumns
        gridView.rowCount =maxRows
        var itemsCount = gridView.columnCount * gridView.rowCount
        if (itemsCount%2 ==0){
            Toast.makeText(this,"is even",Toast.LENGTH_SHORT).show()
        }
        else{
            gridView.columnCount++
            itemsCount = maxColumns * maxRows
            val new = gridView.rowCount * gridView.columnCount
            Toast.makeText(this,"uh oh! we had to add a row, can you figure out why? Hint: we want you to be able to win!",Toast.LENGTH_LONG).show()
        }
        var row = 0
        var column = 0
        for (i in 0 until itemsCount) {
            var view = ImageView(this)
            //change birds!
            when (i % 7) {
                0->view.setBackgroundColor(getColor(R.color.colorBackground))
                1-> view.setBackgroundColor(getColor(R.color.colorAccent))
                2-> view.setBackgroundColor(getColor(R.color.colorAccent))
                3-> view.setBackgroundColor(getColor(R.color.colorBackgroundAccent))
                4-> view.setBackgroundColor(getColor(R.color.colorAccent))
                5->view.setBackgroundColor(getColor(R.color.colorBackgroundAccent))
                6->Picasso.get()
                    .load(R.drawable.face)
                    .resize(50, 50)
                    .centerCrop()
                    .into(view)
            }


            val params = GridLayout.LayoutParams(GridLayout.spec(row, 1.1f), GridLayout.spec(column, 1f))
            view.layoutParams =params
            view.tag="$column/$row"
            view.id = i

            view.setOnClickListener {
                checkTile(i,it.tag as String)
                val dotSizeCurrent=gridView.size
            }
            gridView.addView(view)


            column++

            if (column >= maxColumns) {
                column = 0
                row++
            }




        }

    }


    //check tile will check the tile clicked,
    fun checkTile(position:Int,tag:String){
        Toast.makeText(this,"previous $position -- tag:$tag -- current ${gridView.size} c:${gridView.columnCount}  r:${gridView.rowCount}",Toast.LENGTH_SHORT).show()
    }
  fun picInto(position: Int,resource:Int) {

  }
}
