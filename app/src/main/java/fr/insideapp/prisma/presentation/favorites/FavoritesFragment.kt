package fr.insideapp.prisma.presentation.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import fr.insideapp.prisma.databinding.FragmentFavoritesBinding
import fr.insideapp.prisma.domain.news.model.News
import fr.insideapp.prisma.presentation.news.NewsAdapter
import fr.insideapp.prisma.presentation.news.NewsListener

@AndroidEntryPoint
class FavoritesFragment : Fragment(), NewsListener {

    private val viewModel: FavoritesViewModel by viewModels()
    private var _binding: FragmentFavoritesBinding? = null
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

        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
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
            adapter = NewsAdapter(news, this@FavoritesFragment)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(activity)
        }
    }


    private fun observe() {
        viewModel.getFavoriteNews().observe(viewLifecycleOwner) {
            news.clear()
            news.addAll(it)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onFavoriteClicked(news: News) {
        viewModel.setFavoriteNews(news)
    }
}