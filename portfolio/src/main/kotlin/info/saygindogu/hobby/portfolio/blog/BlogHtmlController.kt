package info.saygindogu.hobby.portfolio.blog

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*


@Controller
@RequestMapping("/blog")
class BlogHtmlController() {


    private val logger = LoggerFactory.getLogger("HtmlController")

    @GetMapping("")
    fun blog(model: Model): String {
        logger.info("Rendering The Page")
        model["title"] = "Blog"
        return "blog"
    }
}