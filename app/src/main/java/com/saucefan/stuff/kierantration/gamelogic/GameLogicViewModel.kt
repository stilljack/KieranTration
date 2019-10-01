package com.saucefan.stuff.kierantration.gamelogic

import android.app.Activity
import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel

import kotlin.random.Random
import com.saucefan.stuff.kierantration.Card
import com.saucefan.stuff.kierantration.R


class GameLogicViewModel(application:Application) : AndroidViewModel(application) {


 val app=application

    //here's where we're gonna store the current game, that way hopefully it will live through view changes.
   /* val LiveList: MutableLiveData<MutableList<Card>> by lazy {
        MutableLiveData<MutableList<Card>>()
    }*/



    companion object {
        var cardOne: Card = Card()
        var cardList = mutableListOf<Card>()
        var gState:GameState = GameState.NEW



    }



    /*    fun gimmieTheListAsLiveData(): MutableLiveData<MutableList<Card>> {
            if (cardList.isNotEmpty()) {
                LiveList.value= cardList
            }
            return LiveList
        }*/


    //init game state

    //check if cardlist already has cards, if so don't remake state
    fun checkCard(newCard: Card):Int {
        //if card matches imemediate previous card do this
        if (cardOne.fronImage==newCard.fronImage) {
            // is a match
            return 1
            }
        //if this is the first card revealed, set cardOne to a new card and let the adapter know to keep the card revealed
        else if (cardOne == Card()) {
            cardOne = newCard
            return 2
        }
        //else we need to reset cardOne to default
        cardOne =
            Card()

        return 3
    }
    fun initGameState(vararg ints:Int) {
        //check if we're in a new game
        if (gState == GameState.NEW) {
            makeCards(ints[0], ints[1], getApplication<Application>().applicationContext)
            gState =GameState.PLAYING
            cardList.shuffle()
        }
        if(gState ==GameState.PLAYING || gState ==GameState.WON) {
            return
        }

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
                val newCardPair = mutableListOf<Card>(
                    Card(),
                    Card()
                )
                val newCard = Card()
                when (i % 7) {
                    0 -> newCard.apply {
                        name = "face"
                        fronImage = R.drawable.face
                        positionInList=i
                        gValue = r.nextInt(0,100)
                    }
                    1 -> newCard.apply {
                        name = "birdone"
                        fronImage = R.drawable.birdone
                        positionInList=i
                        gValue = r.nextInt(0,100)
                    }
                    2 -> newCard.apply {
                        name = "birdtwo"
                        fronImage = R.drawable.birdtwo
                        positionInList=i
                        gValue = r.nextInt(0,100)
                    }
                    3 -> newCard.apply {
                        name = "birdthree"
                        fronImage = R.drawable.birdthree
                        positionInList=i
                        gValue = r.nextInt(0,100)
                    }
                    4 -> newCard.apply {
                        name = "birdfour"
                        fronImage = R.drawable.birdfour
                        positionInList=i
                        gValue =r.nextInt(0,100)
                    }
                    5 -> newCard.apply {
                        name = "birdfive"
                        fronImage = R.drawable.birdfive
                        positionInList=i
                        gValue = r.nextInt(0,100)
                    }
                    6 -> newCard.apply {
                        name = "birdsix"
                        fronImage = R.drawable.birdsix
                        positionInList=i
                        gValue = r.nextInt(0,100)
                    }
                }
                cardList.add(newCard)
                newCard.gValue=r.nextInt(0,100)
                cardList.add(newCard)


            }
            return itemsCount
        }

    }









