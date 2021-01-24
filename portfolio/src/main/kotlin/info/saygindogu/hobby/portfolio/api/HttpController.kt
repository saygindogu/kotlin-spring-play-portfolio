package info.saygindogu.hobby.portfolio.api

import info.saygindogu.hobby.portfolio.blog.Article
import info.saygindogu.hobby.portfolio.blog.ArticleResource
import info.saygindogu.hobby.portfolio.blog.ArticleService
import org.apache.coyote.Response
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*
import java.net.HttpRetryException
import java.net.http.HttpResponse
import java.security.Principal
import org.springframework.web.bind.annotation.PathVariable

import org.springframework.web.bind.annotation.ResponseBody

import org.springframework.web.bind.annotation.GetMapping





@Controller
@RequestMapping("/api")
class ApiHttpController() {

    private val logger = LoggerFactory.getLogger("ApiController")

    @Autowired
    private var articleService: ArticleService? = null

    @PostMapping("articles")
    fun newArticle(@RequestBody body: ArticleResource, principal: Principal): ResponseEntity<ArticleResource> {
        logger.info("Got the Article Creation Request")
        val article = articleService!!.newArticle(principal.name, body)
        return ResponseEntity<ArticleResource>(article, HttpStatus.OK)
    }

    @GetMapping("/articles/{id}")
    @ResponseBody
    fun getArticle(@PathVariable id: String): ResponseEntity<Article> {
        val article = articleService!!.findById(id.toLong())
        return ResponseEntity(article, HttpStatus.OK)
    }

    @GetMapping("/articles/all")
    @ResponseBody
    fun allArticles(): ResponseEntity<List<Article>> {
        val articles = articleService!!.findAll()
        return ResponseEntity(articles, HttpStatus.OK)
    }
}