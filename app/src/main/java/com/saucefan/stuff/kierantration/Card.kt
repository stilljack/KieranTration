package com.saucefan.stuff.kierantration

import android.media.Image

data class Card (
    var name: String?=null,
    var fronImage: Int?=null ,
    var gValue:Int?=null,
    var backImage:Int? =R.drawable.cardback
  //for matching,=

)


/*
   constructor(name: String, fromImage: Int,backImage:Int?,gValue:Int) {
        this.name = name
        this.fronImage = fronImage
        this.backImage = backImage
        this.gValue= gValue

    }
 */