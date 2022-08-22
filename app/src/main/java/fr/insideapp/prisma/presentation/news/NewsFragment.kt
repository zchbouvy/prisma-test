package fr.insideapp.prisma.presentation.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import fr.insideapp.prisma.databinding.FragmentNewsBinding
import fr.insideapp.prisma.domain.news.model.News


@AndroidEntryPoint
class NewsFragment : Fragment(), NewsListener {

    private val viewModel: NewsViewModel by viewModels()
    private var _binding: FragmentNewsBinding? = null
    private lateinit var adapter: NewsAdapter
    private var news: MutableList<News> = mutableListOf()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupRecyclerView()
        observe()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        with(binding) {
            adapter = NewsAdapter(news, this@NewsFragment)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(activity)
            // method for adding swipe to delete functionality
            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.END) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean = false

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val deleteNews: News = news[viewHolder.adapterPosition]
                    viewModel.deleteNews(deleteNews)
                    news.removeAt(viewHolder.adapterPosition)
                    adapter.notifyItemRemoved(viewHolder.adapterPosition)
                }
            }).attachToRecyclerView(recyclerView)
        }
    }


    private fun observe() {
        viewModel.getAllNews().observe(viewLifecycleOwner) {
            news.clear()
            news.addAll(it)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onFavoriteClicked(news: News) {
        viewModel.setFavoriteNews(news)
    }
}