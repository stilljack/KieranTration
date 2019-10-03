package com.saucefan.stuff.kierantration.gamelogic

import android.app.Application
import android.content.Context
import android.os.Debug
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

import kotlin.random.Random
import com.saucefan.stuff.kierantration.Card
import com.saucefan.stuff.kierantration.MyDebugTree
import com.saucefan.stuff.kierantration.R
import timber.log.Timber


class GameLogicViewModel(application:Application) : AndroidViewModel(application) {



 val app=application

    //here's where we're gonna store the current game, that way hopefully it will live through view changes.
   /* val liveList: MutableLiveData<MutableList<Card>> by lazy {
        MutableLiveData<MutableList<Card>>()
    }*/
 val liveList:MutableLiveData<MutableList<Card>>  by lazy {
        MutableLiveData<MutableList<Card>> ()
    }

    companion object {
        var cardOne: Card = Card()
        var cardList = mutableListOf<Card>()
        var gState:GameState = GameState.NEW
        lateinit var cardMap:Map<Int?,Card>

    }

init {
    Timber.plant(MyDebugTree())
}

    fun gimmieTheListAsLiveData(): MutableLiveData<MutableList<Card>> {
            if (cardList.isNotEmpty()) {
                liveList.value= cardList
            }
            return liveList
        }


    fun initGameState(vararg ints:Int):Int {
        //check if we're in a new game
        var result:Int = 0
        if (gState == GameState.NEW) {
           result= makeCards(ints[0], ints[1], getApplication<Application>().applicationContext)
            gState =GameState.PLAYING
            cardList.shuffle()
             cardMap =cardList.associate {
                it.positionInList to it
            }
            val sauce = 1

            Timber.i(cardMap.toString())
            return result
        }
        if(gState ==GameState.PLAYING || gState ==GameState.WON) {
            return 0
        }
        return 0
    }

    //this function consumes the users number of columns and rows and populates cardlist
        fun makeCards(maxColumns: Int, maxRows: Int, conext: Context): Int {
            //make a new random number generator object for us
        val r=Random
            //get total item count
            var itemsCount = maxColumns * maxRows
            if (itemsCount % 2 == 0) {
                Toast.makeText(conext, "is even", Toast.LENGTH_SHORT).show()
                //cool game is winnable
            } else {
                //game would be unwinnable so we add one to row
                itemsCount = maxColumns * (maxRows + 1)
                Toast.makeText(
                    conext,
                    "uh oh! we had to add a row, can you figure out why? Hint: we want you to be able to win!",
                    Toast.LENGTH_LONG
                ).show()
            }
            //so we need to ensure matching!
            //this is, at least to me kind of an interesting problem
            //we want to show as many  different cards as possible but if the number of total cards is not properly divisable,
            //we will end up not having matches, or orphaned cards
            // oh oh! so what we're gonna do, is do two cards at a time, chosen at random sooo
        //as we should only have an even number of items, stepping twice should be fine and possibly improve performance
        // whatever.
        //we're gonna make cards now

            for (i in 0 until itemsCount step 2) {
                var newCardtwo=Card()
                val newCard = Card()
                when (i % 7) {
                    0 -> newCard.apply {
                        name = "face"
                        frontImage = R.drawable.face
                        positionInList=i
                        gValue = r.nextInt(0,1000)
                    }
                    1 -> newCard.apply {
                        name = "birdone"
                        frontImage = R.drawable.birdone
                        positionInList=i
                        gValue = r.nextInt(0,1000)
                    }
                    2 -> newCard.apply {
                        name = "birdtwo"
                        frontImage = R.drawable.birdtwo
                        positionInList=i
                        gValue = r.nextInt(0,1000)
                    }
                    3 -> newCard.apply {
                        name = "birdthree"
                        frontImage = R.drawable.birdthree
                        positionInList=i
                        gValue = r.nextInt(0,1000)
                    }
                    4 -> newCard.apply {
                        name = "birdfour"
                        frontImage = R.drawable.birdfour
                        positionInList=i
                        gValue =r.nextInt(0,1000)
                    }
                    5 -> newCard.apply {
                        name = "birdfive"
                        frontImage = R.drawable.birdfive
                        positionInList=i
                        gValue = r.nextInt(0,1000)
                    }
                    6 -> newCard.apply {
                        name = "birdsix"
                        frontImage = R.drawable.birdsix
                        positionInList=i
                        gValue = r.nextInt(0,1000)
                    }
                }
                cardList.add(newCard)

                //make a copy
                newCardtwo.name=newCard.name
                newCardtwo.positionInList= (i+1)
                newCardtwo.gValue=r.nextInt(0,1000)
                newCardtwo.backImage=newCard.backImage
                newCardtwo.frontImage=newCard.frontImage
                cardList.add(newCardtwo)


            }
            return itemsCount
        }

    }









