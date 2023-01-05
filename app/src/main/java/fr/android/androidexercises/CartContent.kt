package fr.android.androidexercises

class CartContent {

    companion object {

        var cart : MutableList<Book> = ArrayList<Book>()

        fun addToCart(b: Book) {
            cart += b
        }

    }


}