package info.saygindogu.hobby.portfolio.api

import info.saygindogu.hobby.portfolio.blog.Article
import info.saygindogu.hobby.portfolio.blog.ArticleResource
import info.saygindogu.hobby.portfolio.blog.ArticleService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
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

    @GetMapping("/article/{id}")
    @ResponseBody
    fun getArticle(@PathVariable id: String): ResponseEntity<Article> {
        val article = articleService!!.findById(id.toLong())
        return ResponseEntity(article, HttpStatus.OK)
    }

    @GetMapping("/articles/all/")
    @ResponseBody
    fun allArticles(@RequestParam page:String?, @RequestParam perPage: String?): ResponseEntity<List<Article>> {


        val articles = articleService!!.findAll(converToInt(page, 1),converToInt(perPage, 10))
        return ResponseEntity(articles, HttpStatus.OK)
    }

    private fun converToInt(numberString: String?, defaultValue: Int): Int {
        return if (numberString == null){
            defaultValue
        } else {
            Integer.parseInt(numberString)
        }
    }
}