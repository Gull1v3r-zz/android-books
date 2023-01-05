package fr.android.androidexercises

import android.app.Activity
import android.app.AlertDialog
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import com.squareup.picasso.Picasso

class CartBookViewAdapter(private val context: Activity,
                          private val books: List<Book>)
    : ArrayAdapter<Book>(context, R.layout.item_view_book, books) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.item_view_book, null, true)

        val titleText = rowView.findViewById(R.id.titleTextView) as TextView
        val imageView = rowView.findViewById(R.id.iconView) as ImageView
        val synopsisText = rowView.findViewById(R.id.synopsisView) as TextView


        titleText.text = books[position].title
        Picasso.get().load(books[position].cover).into(imageView)
        synopsisText.text = books[position].synopsis.joinToString();

        //OnClick on btn R.id.cartButton
        val button = rowView.findViewById(R.id.cartButton) as Button;
        button.background = ContextCompat.getDrawable(context, R.drawable.cross)


        button.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle("Do you want to remove one item from cart ?")
                .setPositiveButton("Yes !") { dialog, which ->
                    CartContent.cart.remove(books[position])
                    if (CartContent.cart.count { b -> b == books[position] } == 0)
                    {
                        rowView.visibility = View.GONE
                    }
                }
                .setNegativeButton("Cancel", null)
                .show()
        }

        val row = rowView.findViewById(R.id.tableRowView) as TableRow
        row.setOnClickListener {
            synopsisText.visibility = if (synopsisText.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }




        return rowView
    }


}