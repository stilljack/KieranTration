package com.saucefan.stuff.kierantration

import java.io.Serializable

data class Card (

    var name: String?=null,
    var frontImage: Int?=null,
    var positionInList:Int?=null,
    var gValue:Int?=null,
    var backImage:Int? =R.drawable.cardback,
    var facing:Int?=R.drawable.cardback
  //for matching,=

):Serializable
{
    fun flipCard(){
        if (this.facing==this.backImage) {
            this.facing=this.frontImage
        }
        else if (this.facing==frontImage) {
            this.facing=this.backImage
        }
    }
}

/*
   constructor(name: String, fromImage: Int,backImage:Int?,gValue:Int) {
        this.name = name
        this.frontImage = frontImage
        this.backImage = backImage
        this.gValue= gValue

    }
 */