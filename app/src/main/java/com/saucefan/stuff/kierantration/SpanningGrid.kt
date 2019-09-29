package com.saucefan.stuff.kierantration

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

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
