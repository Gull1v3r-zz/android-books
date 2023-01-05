package fr.android.androidexercises

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val list = findViewById(R.id.bookListView) as ListView

        val adapter = CartBookViewAdapter(this, CartContent.cart.distinct())
        list.adapter = adapter

        val button = findViewById(R.id.seeBooksButton) as Button
        button.setOnClickListener {
            val intent = Intent(this,  LibraryActivity::class.java)
            startActivity(intent)
        }

        findViewById<TextView>(R.id.priceView).text = "Prix total : " + CartContent.cart.map { b -> b.price}.sum().toString() + "€"


    }
}
