package com.saucefan.stuff.kierantration



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.grid_view.view.*


class CardAdapter: androidx.recyclerview.widget.ListAdapter<Card,CardAdapter.CardViewHolder>(DIFF_REVIEW_CALLBACK){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.grid_view, parent, false) as View)

    }



    //this is
    companion object {
        private val DIFF_REVIEW_CALLBACK = object : DiffUtil.ItemCallback<Card>() {
            override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
                return oldItem.name == newItem.name &&
                        oldItem.backImage == newItem.backImage &&
                        oldItem.fronImage == newItem.fronImage &&
                        oldItem.gValue == newItem.gValue
            }
        }
    }

    class CardViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val cardFront: ImageView = view.cardFront
        val cardBack:ImageView = view.cardBack



    }
    fun getItemAt(position: Int): Card {
        return getItem(position)
    }




    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val currentCard: Card


        if (position != RecyclerView.NO_POSITION) {
            currentCard=getItemAt(position)
            holder.cardBack = pic
        }


    }








}

