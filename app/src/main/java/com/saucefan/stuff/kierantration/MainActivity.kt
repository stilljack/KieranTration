package com.saucefan.stuff.kierantration

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.size
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.saucefan.stuff.kierantration.GameLogicViewModel.Companion.cardList
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.grid_view.*
import timber.log.Timber


class MainActivity : AppCompatActivity(), CardAdapter.onMatchListener {


    override fun match(cardOne: Card, cardTwo: Card) {
        Toast.makeText(this, cardOne.toString(), Toast.LENGTH_SHORT).show()
        cardList.remove(cardOne)
        cardList.remove(cardTwo)
        recycle_view.adapter?.notifyDataSetChanged()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val model = ViewModelProviders.of(this)[GameLogicViewModel::class.java]

        val rows = intent.getIntExtra("ourRows", 4)
        val columns = intent.getIntExtra("ourColumns", 4)
        val returnValues: List<Int> = makeCard(columns, rows)
        var adapter = CardAdapter(this)
        recycle_view.layoutManager = GridLayoutManager(this, columns)
        recycle_view.adapter = adapter
        if (!cardList.isNullOrEmpty()) {
            cardList.shuffle()
           // adapter.submitList(cardList)
            Timber.i(cardList.toString())
        }
         model.gimmieTheListAsLiveData().observe(this, Observer<MutableList<Card>> {
            updateRecyclerView(adapter,it)
        })

    }


    fun updateRecyclerView(adapter: CardAdapter, list: MutableList<Card>) {
        adapter.submitList(list as List<Card>)
        adapter.notifyDataSetChanged()
    }


    fun makeCard(maxColumns: Int, maxRows: Int): List<Int> {
        //   gridView.columnCount =maxColumns
        //    gridView.rowCount =maxRows
        //get total item count
        var itemsCount = maxColumns * maxRows
        if (itemsCount % 2 == 0) {
            Toast.makeText(this, "is even", Toast.LENGTH_SHORT).show()
            //cool game it winnable
        } else {
            //game would be unwinnable so we add one to row
            itemsCount = maxColumns * (maxRows + 1)
            Toast.makeText(
                this,
                "uh oh! we had to add a row, can you figure out why? Hint: we want you to be able to win!",
                Toast.LENGTH_LONG
            ).show()
        }
        for (i in 0 until itemsCount) {
            //we're gonna make cards now

            val newCard = Card()

            when (i % 7) {
                0 -> newCard.apply {
                    name = "face"
                    fronImage = R.drawable.face
                    gValue = i
                }
                1 -> newCard.apply {
                    name = "birdone"
                    fronImage = R.drawable.birdone
                    gValue = i
                }
                2 -> newCard.apply {
                    name = "birdtwo"
                    fronImage = R.drawable.birdtwo
                    gValue = i
                }
                3 -> newCard.apply {
                    name = "birdthree"
                    fronImage = R.drawable.birdthree
                    gValue = i
                }
                4 -> newCard.apply {
                    name = "birdfour"
                    fronImage = R.drawable.birdfour
                    gValue = i
                }
                5 -> newCard.apply {
                    name = "birdfive"
                    fronImage = R.drawable.birdfive
                    gValue = i
                }
                6 -> newCard.apply {
                    name = "birdsix"
                    fronImage = R.drawable.birdsix
                    gValue = i
                }
            }
            cardList.add(newCard)
        }
        return listOf(maxColumns,maxRows,itemsCount,cardList.size)
    }


        //check tile will check the tile clicked,
        fun checkTile(position: Int, tag: String) {
            Toast.makeText(
                this,
                "previous $position -- tag:$tag -- current ",
                Toast.LENGTH_SHORT
            ).show()
        }

    }


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