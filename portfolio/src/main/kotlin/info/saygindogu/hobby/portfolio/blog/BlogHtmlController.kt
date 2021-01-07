package info.saygindogu.hobby.portfolio.blog

import info.saygindogu.hobby.portfolio.User
import info.saygindogu.hobby.portfolio.format
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException


@Controller
@RequestMapping("/blog")
class BlogHtmlController(private val repository: ArticleRepository) {

    private val logger = LoggerFactory.getLogger("HtmlController")

    @Autowired
    private var articleService: ArticleService? = null


    @GetMapping("/")
    fun blog(model: Model): String {
        val articles = repository
            .findAll()
        model["articles"] = articles.map { it.render() }
        model["title"] = "Blog"
        return "blog"
    }

    @GetMapping("/article/{slug}")
    fun article(@PathVariable slug: String, model: Model): String {
        val article = repository
            .findBySlug(slug)
            ?.render()
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "This article does not exist")
        model["title"] = article.title
        model["article"] = article
        return "article"
    }

    fun Article.render() = RenderedArticle(
        slug,
        title,
        headline,
        content,
        author,
        addedAt.format()
    )

    data class RenderedArticle(
        val slug: String,
        val title: String,
        val headline: String,
        val content: String,
        val author: User,
        val addedAt: String
    )


    @GetMapping("/new-article")
    fun showAddArticle(model: Model): String {
        val article = Article()
        model.addAttribute("add", true)
        model.addAttribute("article", article)
        model.addAttribute("title", "New Blog Post")
        return "new-blog-post"
    }

    @PostMapping("/new-article")
    fun addArticle(
        model: Model,
        @ModelAttribute article: Article
    ): String {
        return try {
            val newArticle = articleService?.save(article)
            "redirect:/article/" + newArticle?.slug.toString()
        } catch (ex: Exception) {
            // log exception first,
            // then show error
            val errorMessage = ex.message
            logger.error(errorMessage)
            model.addAttribute("errorMessage", errorMessage)
            model.addAttribute("add", true)
            model.addAttribute("title", "hebele")
            "new-blog-post"
        }
    }
}