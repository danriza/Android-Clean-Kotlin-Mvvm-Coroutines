package com.codingassignemnt.app.framework.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codingassignemnt.app.R
import com.codingassignemnt.app.business.domain.bean.Article
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class NewsAdapter(
    private var newsItems: List<Article>,
    private val listener: OnNewsArticleClicked
) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.imageview)
        val title: TextView = view.findViewById(R.id.title)
        val time: TextView = view.findViewById(R.id.time)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val layoutId = if (viewType == VIEW_TYPE_FIRST_ITEM) {
            R.layout.news_article_first_item
        } else {
            R.layout.news_article_item
        }
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(layoutId, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = newsItems[position]
        viewHolder.apply {
            itemView.setOnClickListener {
                listener.onNewsItemClicked(newsItems[position])
            }
            title.text = item.title
            item.articleDate?.let {
                time.text = calculateArticleAge(it)
            }
            Glide.with(image.context)
                .load(item.imageUrl)
                .placeholder(R.drawable.placeholder)
                .into(image)
        }
    }

    override fun getItemCount() = newsItems.size

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) VIEW_TYPE_FIRST_ITEM
        else VIEW_TYPE_REGULAR
    }

    fun setItems(items: List<Article>) {
        newsItems = items
    }

    private fun calculateArticleAge(publishedAt: String): String {
        //"publishedAt": "2021-10-04T13:26:57Z",
        val formatter: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val date: Date = formatter.parse(publishedAt) as Date
        val timestamp = date.time
        val difference = System.currentTimeMillis() - timestamp
        val hours = difference / MILLIS_IN_HOUR
        return "$hours H"
    }


    interface OnNewsArticleClicked {
        fun onNewsItemClicked(article: Article)
    }

    companion object {
        private const val VIEW_TYPE_FIRST_ITEM = 0
        private const val VIEW_TYPE_REGULAR = 1
        private const val MILLIS_IN_HOUR = 1000 * 60 * 60
    }

}