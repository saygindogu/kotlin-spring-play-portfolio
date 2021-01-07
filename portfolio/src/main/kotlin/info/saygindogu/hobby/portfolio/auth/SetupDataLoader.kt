package info.saygindogu.hobby.portfolio.auth


import info.saygindogu.hobby.portfolio.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional


@Component
class SetupDataLoader : ApplicationListener<ContextRefreshedEvent?> {
    var alreadySetup = false

    @Autowired
    private var userService: UserService? = null

    @Transactional
    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        if (alreadySetup) return
        /*val readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE")
        val writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE")
        val adminPrivileges: MutableSet<Privilege> = mutableSetOf(
            readPrivilege, writePrivilege
        )
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges)
        createRoleIfNotFound("ROLE_USER", mutableSetOf(readPrivilege))
        val adminRole: Role? = roleRepository?.findByName("ROLE_ADMIN")
        if (adminRole != null) {
            val user = User(
                username = "test",
                firstName = "Test",
                lastName = "Test",
                password = passwordEncoder!!.encode("test"),
                email = "test@test.com",
                roles = mutableSetOf(adminRole),
                enabled = true
            )
            userRepository!!.save<User>(user)
            alreadySetup = true
        }*/

        val username = "test"
        var user = userService!!.findByUsername(username)
        if(user == null) {
            user = User(
                username = username,
                firstName = "Test",
                lastName = "Test",
                password = "test",
                email = "test@test.com",
            )
            println("HEY HO STARTING")

            user = userService!!.signup(user)
            user = userService!!.enable(user)
            userService!!.grantRole(user, "ROLE_ADMIN")
        }

    }
}