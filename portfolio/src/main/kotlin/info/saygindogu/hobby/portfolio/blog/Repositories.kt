package info.saygindogu.hobby.portfolio.blog

import org.springframework.data.repository.PagingAndSortingRepository

interface ArticleRepository : PagingAndSortingRepository<Article, Long> {
    fun findBySlug(slug: String): Article?
    fun findAllByOrderByAddedAtDesc(): Iterable<Article>
}