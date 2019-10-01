package com.saucefan.stuff.kierantration



import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.grid_view.view.*







class CardAdapter(context: Context): androidx.recyclerview.widget.ListAdapter<Card,CardAdapter.CardViewHolder>(DIFF_REVIEW_CALLBACK){
    val contxt=context
    var firstclick=-1
    var secondclick=0
    lateinit var listener:onMatchListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {


        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.grid_view,parent,false)
        val layoutParams =  itemView.layoutParams

        val cv=CardViewHolder(itemView)

        return cv
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

    override fun submitList(list: MutableList<Card>?) {
        super.submitList(list)

    }


    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val currentCard: Card
        if (contxt is onMatchListener){
            listener =contxt
        }

        if (position != RecyclerView.NO_POSITION) {
            currentCard=getItemAt(position)
            Picasso.get()
                .load(currentCard.backImage as Int)
               // .resize(75,75)
                .centerInside()
             //   .fit()
                .into(holder.cardBack)
            holder.cardBack.setOnClickListener{
                listener.match(currentCard,currentCard)

            }
        }


    }
 fun decider(position:Int,context: Context) {
     if (firstclick!=-1) {
         val first:Card =getItemAt(firstclick)
         val second:Card = getItemAt(secondclick)
         if(first.name == second.name){
             Toast.makeText(context,"yay they match!",Toast.LENGTH_SHORT).show()

     }


     }

 }

    interface onMatchListener{
        fun match(cardOne:Card,cardTwo:Card)
    }




}

