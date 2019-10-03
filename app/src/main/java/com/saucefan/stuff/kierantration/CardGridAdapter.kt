package com.saucefan.stuff.kierantration

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.saucefan.stuff.kierantration.gamelogic.GameLogicViewModel.Companion.cardList
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.grid_view.view.*





class CardGridAdapter( val context: Context,val rows:Int,val columns:Int) :BaseAdapter( )
   {

    //   lateinit var dataSource:MutableList<Card>
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    lateinit var listener:onMatchListener
    val display =  context.resources.displayMetrics
    var displayWidth = display.widthPixels;
    var displayHeight = display.heightPixels
    val adjustedWidth =((displayWidth/columns)*0.66).toInt()
    val adjustHeight=((displayHeight/rows)*0.66).toInt()

    val flipedCardPosition:Int =0

    interface onMatchListener{
        fun clickCard(card: Card):Int
    }
    init {
        if (context is CardGridAdapter.onMatchListener){
            listener = context
        }

    }
    override fun getView(position:Int, convertView:View?, parent:ViewGroup): View {
        val tempflip:Boolean=false
        val view: View
        val holder: ViewHolder



        val currentCard:Card=getItem(position)

        if (convertView == null) {


            view =inflater.inflate(R.layout.grid_view,parent,false)


            //i have no idea why this works to keep them from scrolling,
            // this is janky and hacky but it seems to work and i am so fing done struggling with it
           view.layoutParams.height=adjustHeight
            view.layoutParams.width=adjustedWidth

            holder = ViewHolder()
            holder.cardBack = view.cardBack
            holder.cardFront =  view.cardFront


            view.tag = holder
            view.cardBack.tag=currentCard


            Picasso.get()
                .load(currentCard.backImage as Int)
          //      .resize(adjustedWidth,adjustHeight)
        //        .centerInside()
                .into(holder.cardBack)
            Picasso.get()
                .load(currentCard.frontImage as Int)
        //     .resize(adjustedWidth,adjustHeight)
       //         .centerInside()
                .into(holder.cardFront)

            view.setOnClickListener {

                listener.clickCard(currentCard)
                if(holder.cardBack.visibility==View.VISIBLE){
                    holder.cardBack.visibility=View.INVISIBLE
                    holder.cardFront.visibility=View.VISIBLE
                }
                else {
                    holder.cardBack.visibility=View.VISIBLE
                    holder.cardFront.visibility=View.INVISIBLE

                }
            }
        } else {
            // 5
            view = convertView
            holder = convertView.tag as ViewHolder
        }




        return view
    }

    override fun getItem(p0: Int): Card {
        return cardList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {

        return cardList.size
    }
    private class ViewHolder {
        lateinit var cardBack: ImageView
        lateinit var cardFront: ImageView
    }
}

/*

internal class ImageListAdapter internal constructor(context: Context, private val resource: Int, private val itemList: MutableList<Card>)
    : ArrayAdapter<ImageListAdapter.ItemHolder>(context, resource) {


    override fun getCount(): Int {
        return if (this.itemList != null) this.itemList.size else 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView

        val holder: ItemHolder
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_view, parent)
            holder = ItemHolder()
            holder.name = convertView!!.findViewById(R.id.textView)
            holder.icon = convertView.findViewById(R.id.icon)
            convertView.tag = holder
        } else {
            holder = convertView.tag as ItemHolder
        }
        val current = itemList[position]
        holder.name!!.text =
        holder.icon!!.setImageResource(R.mipmap.ic_launcher)

        return convertView
    }

    internal class ItemHolder {
        var name: TextView? = null
        var icon: ImageView? = null
    }
}
*/
