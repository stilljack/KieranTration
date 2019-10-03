package com.saucefan.stuff.kierantration.activities

import android.database.DataSetObserver
import android.os.Bundle
import android.view.ViewGroup
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.saucefan.stuff.kierantration.*
import com.saucefan.stuff.kierantration.gamelogic.GameLogicViewModel.Companion.cardList
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import com.saucefan.stuff.kierantration.gamelogic.GameLogicViewModel
import com.saucefan.stuff.kierantration.gamelogic.GameLogicViewModel.Companion.cardMap


class MainActivity : AppCompatActivity(),



    CardGridAdapter.onMatchListener {
    override fun clickCard(card: Card): Int {

        Timber.i("cardlist.size = ${cardList.size}")
        Timber.i("card recieved from click$card")
        Timber.i("card map result before ${cardMap.get(card.positionInList)}")
        cardMap[card.positionInList]?.flipCard()
        Timber.i("card map result after ${cardMap.get(card.positionInList)}")

        return 0
    }


    private lateinit var gridViewAdapter: CardGridAdapter
    private lateinit var model: GameLogicViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        val display = this.resources.displayMetrics
        var displayWidth = display.widthPixels;
        var displayHeight = display.heightPixels
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // get the view model
        model = ViewModelProviders.of(this)[GameLogicViewModel::class.java]
        var rows = intent.getIntExtra("ourRows", 4)
        val columns = intent.getIntExtra("ourColumns", 4)


        // Set the height by params

        // set height of RecyclerView

        //init/resume/establish game state
        if (model.initGameState(rows, columns) > (rows * columns)) {
            rows++
            //add one if initGameState added a row
        }
        //call and init game state with params
        
        /* var adapter = CardAdapter(this)
      var layoutManager = GridLayoutManager(this, columns)
        recycle_view.layoutManager=layoutManager
        recycle_view.adapter = adapter
*/
        if (!cardList.isNullOrEmpty()) {

            Timber.i(cardList.toString())
            gridViewAdapter = CardGridAdapter(this, rows, columns)
            gridview.adapter = gridViewAdapter
            gridview.numColumns = columns
            gridview.columnWidth = (displayWidth / columns - 20)
        }
            // adapter.submitList(cardList)
            //     gridview.verticalSpacing = 0
            //     gridview.gravity = 77
            /*         gridViewAdapter.registerDataSetObserver(object : DataSetObserver() {
                 override fun onChanged() {
                    Toast.makeText(this@MainActivity,"changed!",Toast.LENGTH_SHORT).show()
                     super.onChanged()
                 }
             })*/


            //    gridview.layoutParams = ViewGroup.
            //   gridview.layoutParams.=(displayHeight/rows)


        /*  model.gimmieTheListAsLiveData().observe(this, Observer<MutableList<Card>> {
            // updateRecyclerView(adapter,it)
            updateGridView(gridViewAdapter)
        })
    }
*/

        fun updateGridView(adapter: CardGridAdapter) {
            adapter.notifyDataSetChanged()

        }
        /*   fun updateGridView (adapter: CardGridAdapter) {

            adapter.notifyDataSetChanged()
        }
    fun updateRecyclerView(adapter: CardAdapter, list: MutableList<Card>) {
        adapter.submitList(list)
        adapter.notifyDataSetChanged()
    }
*/




        //check tile will check the tile clicked,
        fun checkTile(position: Int, tag: String) {
            Toast.makeText(
                this,
                "previous $position -- tag:$tag -- current ",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    }

/*  fun match(cardOne: Card, cardTwo: Card) {
        Toast.makeText(this, cardOne.toString(), Toast.LENGTH_SHORT).show()
        updateGridView(gridViewAdapter)
    }*/
/*
  //   gridView.columnCount =maxColumns
    //    gridView.rowCount =maxRows
        //get total item count
        var itemsCount = maxColumns * maxRows
        if (itemsCount%2 ==0){
            Toast.makeText(this,"is even",Toast.LENGTH_SHORT).show()
            //cool game it winnable
        }
        else{
            //game would be unwinnable so we add one to row
            itemsCount=maxColumns *(maxRows+1)
            Toast.makeText(this,"uh oh! we had to add a row, can you figure out why? Hint: we want you to be able to win!",Toast.LENGTH_LONG).show()
        }
        var row = 0
        var column = 0
        for (i in 0 until itemsCount) {
            //we're gonna make cards now
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



 */