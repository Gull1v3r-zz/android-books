package fr.android.androidexercises

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

@ExperimentalCoroutinesApi
class LibraryActivity : AppCompatActivity() {


    private fun displayBooks(view: ListView, libraryActivity: LibraryActivity){

        GlobalScope.launch {

            // Coroutine non utilisée


        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)

        val l = findViewById<ListView>(R.id.bookListView)


        // Plant logger cf. Android Timber
        Timber.plant(Timber.DebugTree())

        val retrofit = Retrofit.Builder()
            .baseUrl("https://henri-potier.techx.fr/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // TODO create a service
        val service = retrofit.create(HenryPotierService::class.java)

        val libraryActivity = this

        // TODO enqueue call and display book title
        service.getListBooks().enqueue(object : Callback<List<Book>> {
            override fun onResponse(call: Call<List<Book>>, response: Response<List<Book>>) {
                val books = response.body()!!
                val arrayAdapter = BookViewAdapter(libraryActivity, books.map { b -> b.title }, books.map { b -> b.cover })
                l.adapter = arrayAdapter

            }

            override fun onFailure(call: Call<List<Book>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

        //TODO Afficher sur le FRONT

        displayBooks(l, this)




        // TODO log books
        // TODO display book as a list

    }


}
