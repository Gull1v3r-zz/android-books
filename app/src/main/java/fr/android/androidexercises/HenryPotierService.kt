package fr.android.androidexercises

import retrofit2.Call
import retrofit2.http.GET

interface HenryPotierService {
    // TODO Method GET books which return a List<Book>
    @GET("books")
    fun getListBooks(): Call<List<Book>>

}
