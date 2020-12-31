package info.saygindogu.hobby.portfolio.blog

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(collectionResourceRel = "articles", path = "articles")
interface ArticleRepository : PagingAndSortingRepository<Article, Long> {
    fun findBySlug(slug: String): Article?
    fun findAllByOrderByAddedAtDesc(): Iterable<Article>
}

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
interface UserRepository : CrudRepository<User, Long> {
    fun findByLogin(login: String): User?
}