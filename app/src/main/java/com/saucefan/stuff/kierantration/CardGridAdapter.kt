package com.saucefan.stuff.kierantration

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.saucefan.stuff.kierantration.GameLogicViewModel.Companion.cardList
import com.squareup.picasso.Picasso


class CardGridAdapter( val context: Context) :BaseAdapter( ) {
 //   lateinit var dataSource:MutableList<Card>
   // private val inflater: LayoutInflater = LayoutInflater.from(context)
    lateinit var listener:onMatchListener
    val flipedCardPosition:Int =0
    interface onMatchListener{
        fun clickCard(card: Card):Boolean
    }

    override fun getView(position:Int, convertView:View?, parent:ViewGroup): View {
        val currentCard:Card=getItem(position)
          val view: ImageView= ImageView(context)
          view.tag=currentCard


          Picasso.get()
              .load(currentCard.fronImage as Int)
              .resize(75,75)
              .centerInside()
              .into(view)

        if (context is CardGridAdapter.onMatchListener){
            listener = context
        }
        view.setOnClickListener {
            if(listener.clickCard(currentCard)){

            }
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
