package fr.insideapp.prisma.presentation.news

import fr.insideapp.prisma.domain.news.model.News

interface NewsListener {
    fun onFavoriteClicked(news: News)
}