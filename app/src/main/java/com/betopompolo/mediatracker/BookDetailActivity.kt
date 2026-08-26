package com.betopompolo.mediatracker

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.google.android.material.progressindicator.LinearProgressIndicator

var bookDetail = BookDetail(
    name = "The Lord of the Rings: The Fellowship of the Ring",
    synopsis = "The Lord of the Rings: The Fellowship of the Ring is a 2001 epic fantasy film directed by Peter Jackson from a screenplay by Fran Walsh, Philippa Boyens, and Jackson. It is based on J. R. R. Tolkien's 1954 The Fellowship of the Ring, the first volume of the novel The Lord of the Rings. The film is the first instalment in The Lord of the Rings trilogy. It features an ensemble cast including Elijah Wood, Ian McKellen, Liv Tyler, Viggo Mortensen, Sean Astin, Cate Blanchett, John Rhys-Davies, Billy Boyd, Dominic Monaghan, Orlando Bloom, Christopher Lee, Hugo Weaving, Sean Bean, Ian Holm, and Andy Serkis.",
    currentPage = 20,
    totalPages = 200,
    author = "J.R.R. Tolkien",
    publisher = "George Allen & Unwin",
    firstPublishedAt = "July 29, 1954",
    genres = listOf("Fantasy", "Adventure", "Epic")
)
class BookDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.book_detail_layout)

        val bookTitleTextView = findViewById<TextView>(R.id.book_title_text_view)
        bookTitleTextView.text = bookDetail.name

        val synopsisTextView = findViewById<TextView>(R.id.synopsis_text_view)
        synopsisTextView.text = bookDetail.synopsis

        val pageCount = findViewById<TextView>(R.id.page_count)
        pageCount.text = "${bookDetail.currentPage} of ${bookDetail.totalPages}"

        findViewById<TextView>(R.id.reading_progress_percentage).apply {
            text = "${bookDetail.progress}%"
        }

        findViewById<LinearProgressIndicator>(R.id.linear_progress_reading_progress).apply {
            progress = bookDetail.progress.toInt()
        }

        findViewById<TextView>(R.id.author).apply {
            text = bookDetail.author
        }

        findViewById<TextView>(R.id.publisher).apply {
            text = bookDetail.publisher
        }

        findViewById<TextView>(R.id.first_published).apply {
            text = bookDetail.firstPublishedAt
        }

        findViewById<TextView>(R.id.genres_text_view).apply {
            text = bookDetail.genres.joinToString(" | ")
        }
    }
}

data class BookDetail(
    val name: String,
    val synopsis: String,
    val currentPage: Int,
    val totalPages: Int,
    val author: String,
    val publisher: String,
    val firstPublishedAt: String,
    val genres: List<String>
) {
    val progress: Float
        get() = (totalPages / currentPage).toFloat()
}
