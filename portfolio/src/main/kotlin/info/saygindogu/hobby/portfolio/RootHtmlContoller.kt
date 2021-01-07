package info.saygindogu.hobby.portfolio

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import javax.servlet.ServletContext

@Controller
class RootHtmlContoller() {
    @Autowired
    private var context: ServletContext? = null


    @GetMapping("/login")
    fun login(model: Model): String {
        model["title"] = "Title"
        model["contextPath"] = context!!.getContextPath()
        return "login"
    }

    @GetMapping("/signup")
    fun signup(model: Model): String {
        model["title"] = "Title"
        model["contextPath"] = context!!.getContextPath()
        return "signup"
    }

    @GetMapping("/")
    fun home(model: Model): String {
        model["title"] = "Title"
        val auth: Authentication = SecurityContextHolder.getContext().authentication
        val username: String = auth.name
        val roles: String = auth.authorities.toString()
        model["username"] = username
        model["roles"] = roles
        model["contextPath"] = context!!.getContextPath()
        return "home"
    }
}