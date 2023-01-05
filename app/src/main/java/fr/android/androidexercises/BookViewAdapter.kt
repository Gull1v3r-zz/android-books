package fr.android.androidexercises

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
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
                      private val books: List<Book>)
    : ArrayAdapter<Book> (context, R.layout.item_view_book, books){

    fun updateCartCount(button: Button, position: Int) {
        val nbOfElInCart = CartContent.cart.count { b -> b == books[position] }
        if(nbOfElInCart > 0) {
            button.text = nbOfElInCart.toString()
        } else
        {
            button.text = ""
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.item_view_book, null, true)

        val titleText = rowView.findViewById(R.id.titleTextView) as TextView
        val imageView = rowView.findViewById(R.id.iconView) as ImageView
        val synopsisText = rowView.findViewById(R.id.synopsisView) as TextView


        titleText.text = books[position].title + "\n" + books[position].price + "€"
        Picasso.get().load(books[position].cover).into(imageView)
        synopsisText.text = books[position].synopsis.joinToString();

        //OnClick on btn R.id.cartButton
        val button = rowView.findViewById(R.id.cartButton) as Button;

        updateCartCount(button, position)

        button.setOnClickListener {
            CartContent.cart += books[position]
            AlertDialog.Builder(context)
                .setTitle("Added to cart successfully !")
                .setPositiveButton("OK !", null)
                .setNegativeButton("Cancel") { dialog, which ->
                    CartContent.cart.removeLast()
                    updateCartCount(button, position)
                }
                .show()
            updateCartCount(button,position)
        }

        val row = rowView.findViewById(R.id.tableRowView) as TableRow
        row.setOnClickListener {
            synopsisText.visibility = if (synopsisText.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }




        return rowView
    }


}
