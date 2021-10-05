package com.codingassignemnt.app.ui

import android.app.Activity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.codingassignemnt.app.R
import com.codingassignemnt.app.databinding.ActivityNewsDetailBinding

class NewsDetailActivity : Activity() {
    private lateinit var binding: ActivityNewsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        populateView()
        binding.backArrow.setOnClickListener {
            finish()
        }
    }

    private fun populateView() {
        binding.newsTitle.text = intent.getStringExtra(EXTRA_KEY_TITLE)
        binding.newsDescription.text = intent.getStringExtra(EXTRA_KEY_DESCRIPTION)
        binding.newsAuthor.text = intent.getStringExtra(EXTRA_KEY_AUTHOR)
        val imageUrl = intent.getStringExtra(EXTRA_KEY_IMAGE_URL)
        Glide.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.placeholder)
            .into(binding.newsImage)
    }

    companion object {
        const val EXTRA_KEY_TITLE = "extra_key_title"
        const val EXTRA_KEY_DESCRIPTION = "extra_key_description"
        const val EXTRA_KEY_IMAGE_URL = "extra_key_image_url"
        const val EXTRA_KEY_AUTHOR = "extra_key_author"
    }
}

