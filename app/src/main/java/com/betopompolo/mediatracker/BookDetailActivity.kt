package com.betopompolo.mediatracker

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.betopompolo.mediatracker.databinding.BookDetailLayoutBinding

var bookDetail = BookDetail(
    name = "The Lord of the Rings: The Fellowship of the Ring",
    synopsis = "The Lord of the Rings: The Fellowship of the Ring is a 2001 epic fantasy film directed by Peter Jackson from a screenplay by Fran Walsh, Philippa Boyens, and Jackson. It is based on J. R. R. Tolkien's 1954 The Fellowship of the Ring, the first volume of the novel The Lord of the Rings. The film is the first instalment in The Lord of the Rings trilogy. It features an ensemble cast including Elijah Wood, Ian McKellen, Liv Tyler, Viggo Mortensen, Sean Astin, Cate Blanchett, John Rhys-Davies, Billy Boyd, Dominic Monaghan, Orlando Bloom, Christopher Lee, Hugo Weaving, Sean Bean, Ian Holm, and Andy Serkis.",
    totalPages = 200,
    author = "J.R.R. Tolkien",
    publisher = "George Allen & Unwin",
    firstPublishedAt = "July 29, 1954",
    genres = listOf("Fantasy", "Adventure", "Epic")
)

class BookDetailActivity : ComponentActivity() {
    private lateinit var binding: BookDetailLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = BookDetailLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bookTitleTextView.text = bookDetail.name
        binding.synopsisTextView.text = bookDetail.synopsis
        binding.author.text = bookDetail.author
        binding.publisher.text = bookDetail.publisher
        binding.firstPublished.text = bookDetail.firstPublishedAt
        binding.genresTextView.text = bookDetail.genres.joinToString(" | ")
        binding.fabPreviousPage.setOnClickListener {
            bookDetail.getPreviousPage()?.let {
                bookDetail = it
                updateReadingProgressViews()
            }
        }
        binding.fabNextPage.setOnClickListener {
            bookDetail.getNextPage()?.let { nextPageDetail ->
                bookDetail = nextPageDetail
                updateReadingProgressViews()
            }
        }
        createBadgesViews(
            listOf(
                "Book",
                bookDetail.genres.first(),
                "${bookDetail.totalPages} pages",
                "9.8",
            )
        )
        updateReadingProgressViews()
    }

    private fun createBadgesViews(badges: List<String>) {
        val maxBadgesPerRow = 3
        for (badge in badges.take(maxBadgesPerRow)) {
            val badgeLayout = LayoutInflater.from(this).inflate(R.layout.badge_layout, null, false)
            badgeLayout.findViewById<TextView>(R.id.badge_text_view).text = badge
            binding.bookBadgeRow1.addView(badgeLayout)
        }

        for (badge in badges.drop(maxBadgesPerRow).take(maxBadgesPerRow)) {
            val badgeLayout = LayoutInflater.from(this).inflate(R.layout.badge_layout, null, false)
            badgeLayout.findViewById<TextView>(R.id.badge_text_view).text = badge
            binding.bookBadgeRow2.addView(badgeLayout)
        }
    }

    private fun updateReadingProgressViews() {
        binding.pageCount.text =
            getString(R.string.of, bookDetail.currentPage, bookDetail.totalPages)
        binding.readingProgressPercentage.text = "${bookDetail.progress}%"
        binding.linearProgressReadingProgress.progress = bookDetail.progress.toInt()
    }
}

data class BookDetail(
    val name: String,
    val synopsis: String,
    val currentPage: Int = 0,
    val totalPages: Int,
    val author: String,
    val publisher: String,
    val firstPublishedAt: String,
    val genres: List<String>
) {
    init {
        require(totalPages > 0) { "Total pages must be greater than zero" }
        require(currentPage in currentPageValidRange) { "Current page must be greater than zero and less than or equal to total pages" }
    }

    private val currentPageValidRange: IntRange
        get() = 0..totalPages

    val progress: Float
        get() = (currentPage.toFloat() / totalPages) * 100

    fun getPreviousPage(): BookDetail? {
        if (currentPage > 0) {
            return copy(currentPage = currentPage - 1)
        }
        return null
    }

    fun getNextPage(): BookDetail? {
        val next = currentPage + 1
        return if (next in currentPageValidRange) copy(currentPage = next) else null
    }
}
