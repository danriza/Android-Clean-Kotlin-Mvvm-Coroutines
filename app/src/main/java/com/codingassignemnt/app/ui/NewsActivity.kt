package com.codingassignemnt.app.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.codingassignemnt.app.R
import com.codingassignemnt.app.databinding.ActivityNewsListBinding
import com.codingassignemnt.app.ui.NewsDetailActivity.Companion.EXTRA_KEY_AUTHOR
import com.codingassignemnt.app.ui.NewsDetailActivity.Companion.EXTRA_KEY_DESCRIPTION
import com.codingassignemnt.app.ui.NewsDetailActivity.Companion.EXTRA_KEY_IMAGE_URL
import com.codingassignemnt.app.ui.NewsDetailActivity.Companion.EXTRA_KEY_TITLE


class NewsActivity : AppCompatActivity(), NewsAdapter.OnNewsArticleClicked {
    private lateinit var viewModel: NewsViewModel
    private val adapter = NewsAdapter(mutableListOf(), this)
    private lateinit var binding: ActivityNewsListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNewsList()
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        setupViewModelObersers()
        viewModel.getNews()
    }

    override fun onNewsItemClicked(newsItem: NewsUiModel) {
        showNewsDetails(newsItem.title, newsItem.description, newsItem.imageUrl, newsItem.author)
    }

    private fun setupViewModelObersers() {
        viewModel.newsListLiveData.observe(this, {
            adapter.setItems(it)
            adapter.notifyDataSetChanged()
        })

        viewModel.errorMessage.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.loading.observe(this, Observer {
            if (it) {
                binding.progressDialog.visibility = View.VISIBLE
            } else {
                binding.progressDialog.visibility = View.GONE
            }
        })
    }

    private fun setupNewsList() {
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        val horizontalDecoration = DividerItemDecoration(
            this,
            DividerItemDecoration.VERTICAL
        )
        val horizontalDivider =
            ContextCompat.getDrawable(this, R.drawable.horizontal_divier)
        horizontalDivider?.let {
            horizontalDecoration.setDrawable(it)
        }
        binding.recyclerview.addItemDecoration(horizontalDecoration)
        binding.recyclerview.adapter = adapter
    }

    private fun showNewsDetails(
        title: String?,
        description: String?,
        urlToImage: String?,
        author: String?
    ) {
        val intent = Intent(this, NewsDetailActivity::class.java)
        intent.putExtra(EXTRA_KEY_TITLE, title)
        intent.putExtra(EXTRA_KEY_DESCRIPTION, description)
        intent.putExtra(EXTRA_KEY_IMAGE_URL, urlToImage)
        intent.putExtra(EXTRA_KEY_AUTHOR, author)
        startActivity(intent)
    }
}

