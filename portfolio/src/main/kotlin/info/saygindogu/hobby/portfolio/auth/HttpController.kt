package info.saygindogu.hobby.portfolio.auth

import info.saygindogu.hobby.portfolio.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import javax.servlet.ServletContext


@Controller
class HttpController {
    @Autowired
    private var context: ServletContext? = null

    @Autowired
    private var userService: UserService? = null


    @PostMapping("/signup")
    fun signup(@RequestParam body: Map<String, String>, model: Model): String{
        userService!!.signup(
            User(email = body.get("email"),
            username = body.get("username"),
            password = body.get("password")
        )
        )
        model["title"] = "Title"
        model["contextPath"] = context!!.getContextPath()
        return "home"
    }


    @GetMapping("/showMyLoginPage")
    fun showMyLoginPage(model:Model): String {
        model["title"] = "Login"
        model["contextPath"] = context!!.getContextPath()
        return "login"
    }

    @GetMapping("/access-denied")
    fun showAccessDenied(model:Model): String {
        model["title"] = "Login"
        model["contextPath"] = context!!.getContextPath()
        return "access-denied"
    }
}
