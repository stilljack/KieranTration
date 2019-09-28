package com.saucefan.stuff.kierantration

import android.media.Image

class Card {
    var name: String? = null
    var fronImage: Int? = null
    var backImage:Int? =null
  //for matching
    var gValue:Int? =null

    constructor(name: String, fromImage: Int,backImage:Int?,gValue:Int) {
        this.name = name
        this.fronImage = fronImage
        this.backImage = backImage
        this.gValue= gValue

    }
}