package com.saucefan.stuff.kierantration

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


//View model should:
//be able to provide
//Lists<FoodieEntries> from the db such that
//by date
//most recent x posts
//whatever else
//
//

//should be able to dispatch a create event to the database
//should be able to tell the sync service to updateReview certain files, deleteReview certain files, restore if db is blank


class GameLogicViewModel(application:Application) : AndroidViewModel(application) {
    //here's where we're gonna store the current game, that way hopefully it will live through view changes.
    val LiveList: MutableLiveData<MutableList<Card>> by lazy {
        MutableLiveData<MutableList<Card>>()
    }
    companion object {
        var cardList = mutableListOf<Card>()
    }

        fun gimmieTheListAsLiveData(): MutableLiveData<MutableList<Card>> {
            if (cardList.isNotEmpty()) {
                LiveList.value= cardList
            }
            return LiveList
        }
        fun initCardList(list: List<Card>) {
            cardList=list as MutableList<Card>
        }
}