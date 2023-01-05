package fr.android.androidexercises

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class BookViewAdapter(private val context:Activity, private val titles: List<String>, private val imagesURL: List<String>)
    : ArrayAdapter<String> (context, R.layout.item_view_book, titles){


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.item_view_book, null, true)

        val titleText = rowView.findViewById(R.id.titleTextView) as TextView
        val imageView = rowView.findViewById(R.id.iconView) as ImageView

        titleText.text = titles[position]
        println(imagesURL[position])
        Picasso.get().load(imagesURL[position]).into(imageView)

        return rowView
    }

}