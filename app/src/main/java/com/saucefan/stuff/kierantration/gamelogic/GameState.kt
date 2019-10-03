package com.saucefan.stuff.kierantration.gamelogic

import com.saucefan.stuff.kierantration.Card
import com.saucefan.stuff.kierantration.gamelogic.GameLogicViewModel.Companion.cardList



fun checkCard(newCard: Card):Int {
    //if card matches imemediate previous card do this
    if (GameLogicViewModel.cardOne.frontImage==newCard.frontImage) {
        // is a match
        return 1
    }
    //if this is the first card revealed, set cardOne to a new card and let the adapter know to keep the card revealed
    else if (GameLogicViewModel.cardOne == Card()) {
        GameLogicViewModel.cardOne = newCard
        return 2
    }
    //else we need to reset cardOne to default
    GameLogicViewModel.cardOne =
        Card()
    return 3
}









enum class GameState {
    NEW {

    },
    PLAYING {

fun shuffle(){
    cardList.shuffle()
}
    },

    WON {

    }

}


