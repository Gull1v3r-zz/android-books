package fr.android.androidexercises

import android.app.Activity
import android.opengl.Visibility
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TableRow
import android.widget.TextView
import com.squareup.picasso.Picasso

class BookViewAdapter(private val context:Activity,
                      private val titles: List<String>,
                      private val imagesURL: List<String>,
                      private val synopsis: List<List<String>>)
    : ArrayAdapter<String> (context, R.layout.item_view_book, titles){


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.item_view_book, null, true)

        val titleText = rowView.findViewById(R.id.titleTextView) as TextView
        val imageView = rowView.findViewById(R.id.iconView) as ImageView
        val synopsisText = rowView.findViewById(R.id.synopsisView) as TextView


        titleText.text = titles[position]
        Picasso.get().load(imagesURL[position]).into(imageView)
        synopsisText.text = synopsis[position].joinToString();

        //OnClick on btn R.id.cartButton
        val button = rowView.findViewById(R.id.cartButton) as Button;
        button.setOnClickListener {
            println("ADDING TO CART")
            val bName = titles[position];
            val bImage = imagesURL[position];
            //INCREMENTER LE NB LIVRE DANS LE PANIER + ENVOYER CA A LIBRARY ACTIVITY
        // data.add(arrayOf(bName, bImage));
        }

        val row = rowView.findViewById(R.id.tableRowView) as TableRow
        row.setOnClickListener {
            synopsisText.visibility = if (synopsisText.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }




        return rowView
    }

}