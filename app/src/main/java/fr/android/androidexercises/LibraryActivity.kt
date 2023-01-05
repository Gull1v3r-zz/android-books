package fr.android.androidexercises

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

class LibraryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)
        var books : MutableList<Book> = ArrayList();

        // Plant logger cf. Android Timber
        Timber.plant(Timber.DebugTree())

        // TODO build Retrofit

        val retrofit = Retrofit.Builder()
            .baseUrl("https://henri-potier.techx.fr/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // TODO create a service
        val service = retrofit.create(HenryPotierService::class.java)

        // TODO enqueue call and display book title

        service.getListBooks().enqueue(object : Callback<List<Book>> {
            override fun onResponse(call: Call<List<Book>>, response: Response<List<Book>>) {
                books = response.body()?.toMutableList()!!
            }

            override fun onFailure(call: Call<List<Book>>, t: Throwable) {
                Timber.d(t.message)
            }
        })

        // TODO log books
        // TODO display book as a list

        Timber.d(books.toString())
    }

}
