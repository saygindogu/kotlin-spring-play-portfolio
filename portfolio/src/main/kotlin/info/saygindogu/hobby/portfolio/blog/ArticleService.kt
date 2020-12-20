package info.saygindogu.hobby.portfolio.blog


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import java.lang.Exception
import java.util.ArrayList

@Service
class ArticleService {
    @Autowired
    private val articleRepository: ArticleRepository? = null

    @Autowired
    private val userRepository: UserRepository? = null

    private fun existsById(id: Long): Boolean {
        return articleRepository!!.existsById(id)
    }

    fun findById(id: Long): Article? {
        return articleRepository!!.findById(id).orElse(null)
    }

    fun findAll(pageNumber: Int, rowPerPage: Int): List<Article> {
        val articles: ArrayList<Article> = ArrayList<Article>()
        val sortedByLastUpdateDesc: Pageable = PageRequest.of(
            pageNumber - 1, rowPerPage,
            Sort.by("id").ascending()
        )
        articleRepository!!.findAll(sortedByLastUpdateDesc).forEach(articles::add)
        return articles
    }

    @Throws(Exception::class)
    fun save(article: Article?): Article {
        if (article == null) {
            throw Exception()
        }
        if (article.title == "") {
            throw Exception("Title is required")
        }
        if (article.headline == "") {
            throw Exception("Headline is required")
        }
        if (article.content == "") {
            throw Exception("Content is required")
        }
        val author = User(
            login = "Test",
            firstname = "Saygin",
            lastname = "Dogu",
        )
        article.author = userRepository!!.save(author)
        return articleRepository!!.save(article)
    }

    @Throws(Exception::class)
    fun update(article: Article) {
        if (article.title == "") {
            throw Exception("Title is required")
        }
        if (article.headline == "") {
            throw Exception("Headline is required")
        }
        if (article.content == "") {
            throw Exception("Content is required")
        }
        article.author = User(
            login = "Test",
            firstname = "Saygin",
            lastname = "Dogu",
        )
        articleRepository!!.save<Article>(article)
    }

    @Throws(Exception::class)
    fun deleteById(id: Long) {
        if (!existsById(id)) {
            throw Exception("Cannot find Article with id: $id")
        } else {
            articleRepository!!.deleteById(id)
        }
    }

    fun count(): Long {
        return articleRepository!!.count()
    }
}