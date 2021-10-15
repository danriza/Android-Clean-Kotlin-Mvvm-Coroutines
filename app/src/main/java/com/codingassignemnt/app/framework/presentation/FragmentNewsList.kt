package com.codingassignemnt.app.framework.presentation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.codingassignemnt.app.R
import com.codingassignemnt.app.business.domain.bean.Article
import com.codingassignemnt.app.business.domain.state.DataState
import com.codingassignemnt.app.databinding.FragmentNewsListBinding
import com.codingassignemnt.app.framework.presentation.MainStateEvent.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class FragmentNewsList : Fragment(R.layout.fragment_news_list), NewsAdapter.OnNewsArticleClicked {

    private lateinit var binding: FragmentNewsListBinding
    private val adapter = NewsAdapter(mutableListOf(), this)
    private val viewModel: NewsListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNewsList()
        subscribeObservers()
        viewModel.setStateEvent(GetArticlesEvent)
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            when (dataState) {
                is DataState.Success<List<Article>> -> {
                    displayProgressBar(false)
                    refreshList(dataState.data)
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.errorMessage)
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    private fun displayError(message: String?) {
       Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }

    private fun refreshList(articles: List<Article>) {
        adapter.setItems(articles)
        adapter.notifyDataSetChanged()
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        if (isDisplayed) {
            binding.progressDialog.visibility = View.VISIBLE
        } else {
            binding.progressDialog.visibility = View.GONE
        }
    }

    private fun setupNewsList() {
        binding.recyclerview.layoutManager = LinearLayoutManager(activity)
        val horizontalDecoration = DividerItemDecoration(
            activity,
            DividerItemDecoration.VERTICAL
        )
        val horizontalDivider =
            ContextCompat.getDrawable(requireActivity(), R.drawable.horizontal_divier)
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
        val intent = Intent(requireActivity(), NewsDetailActivity::class.java)
        intent.putExtra(NewsDetailActivity.EXTRA_KEY_TITLE, title)
        intent.putExtra(NewsDetailActivity.EXTRA_KEY_DESCRIPTION, description)
        intent.putExtra(NewsDetailActivity.EXTRA_KEY_IMAGE_URL, urlToImage)
        intent.putExtra(NewsDetailActivity.EXTRA_KEY_AUTHOR, author)
        startActivity(intent)
    }

    override fun onNewsItemClicked(article: Article) {
        showNewsDetails(article.title, article.description, article.imageUrl, article.author)
    }
}