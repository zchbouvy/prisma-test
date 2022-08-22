package fr.insideapp.prisma.presentation.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.insideapp.prisma.R
import fr.insideapp.prisma.databinding.ItemNewsBinding
import fr.insideapp.prisma.domain.news.model.News
import fr.insideapp.prisma.domain.util.DateFormatter

class NewsAdapter(
    private val items: List<News>,
    private val newsListener: NewsListener
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemBinding =
            ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    override fun getItemCount(): Int = items.size

    inner class NewsViewHolder(private val itemBinding: ItemNewsBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(news: News, position: Int) {
            with(itemBinding) {
                Glide.with(newsImage.context)
                    .asBitmap()
                    .load(news.imageUrl)
                    .placeholder(R.drawable.news_placeholder)
                    .into(newsImage)

                newsTitle.text = news.title
                newsPublishedDate.text = news.published?.let { DateFormatter.format(it) } ?: ""
                newsFavorite.isActivated = news.isFavorite

                newsFavorite.setOnClickListener {
                    news.isFavorite = !news.isFavorite
                    notifyItemChanged(position)
                    newsListener.onFavoriteClicked(news)
                }
            }
        }
    }
}