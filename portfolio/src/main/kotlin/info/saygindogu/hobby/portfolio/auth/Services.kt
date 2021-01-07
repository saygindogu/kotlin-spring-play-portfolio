package info.saygindogu.hobby.portfolio.auth


import info.saygindogu.hobby.portfolio.Authority
import info.saygindogu.hobby.portfolio.AuthorityRepository
import info.saygindogu.hobby.portfolio.User
import info.saygindogu.hobby.portfolio.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

interface UserService {
    // CRUD
    fun getUsers(): Iterable<User>?
    fun saveUser(theUser: User): User
    fun getUser(theId: Long): Optional<User>
    fun deleteUser(theId: Long)
    fun findByUsername(username: String): User?


    fun signup(user: User): User
    fun enable(user: User): User
    fun grantRole(user: User, role: String): User

}


@Service
class UserServiceImpl() : UserService {
    @Autowired
    private var userRepository: UserRepository? = null

    @Autowired
    private var authorityRepository: AuthorityRepository? = null


    @Autowired
    private var passwordEncoder: PasswordEncoder? = null


    override fun getUsers(): Iterable<User>? {
        return userRepository!!.findAll()
    }

    override fun saveUser(theUser: User): User {
        return userRepository!!.save(theUser)
    }

    override fun getUser(theId: Long): Optional<User> {
        return userRepository!!.findById(theId)
    }

    override fun deleteUser(theId: Long) {
        userRepository!!.deleteById(theId)
    }

    override fun findByUsername(username: String): User? {
        return userRepository!!.findByUsername(username)
    }

    override fun signup(user: User): User {
        println("HEY HO SIGNUP")
        user.enabled = false
        val email = user.email
        val username = user.username
        if (email != null
            && username != null
            && userRepository!!.findByEmail(email) == null
            && userRepository!!.findByUsername(username) == null
        ) {
            user.password = passwordEncoder!!.encode(user.password)
            val savedUser = userRepository!!.save(user)
            val authority = Authority(
                username = savedUser.username,
                authority = "ROLE_USER"
            )
            authorityRepository!!.save<Authority>(authority)
            return savedUser
        }
        return user
    }

    override fun enable(user: User): User {
        println("HEY HO ENABLE")
        if (user.id == 0L) return user
        user.enabled = true
        return userRepository!!.save(user)
    }

    override fun grantRole(user: User, role: String): User {
        println("HEY HO ROLE")
        if (user.id == 0L) return user
        val username = user.username
        if (username != null && authorityRepository!!.findByUsernameAndAuthority(username, role) == null) {
            val authority = Authority(
                username = username,
                authority = role
            )
            authorityRepository!!.save<Authority>(authority)
        }
        return user
    }
}