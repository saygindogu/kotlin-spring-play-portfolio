package info.saygindogu.hobby.portfolio

import javax.persistence.*

@Entity
@Table(
    name = "users",
    uniqueConstraints = [UniqueConstraint(columnNames = arrayOf("username")), UniqueConstraint(columnNames = arrayOf("email"))]
)
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    var username: String? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var email: String? = null,
    var password: String? = null,
    var enabled: Boolean = false,
    var tokenExpired: Boolean = false,
    var description: String? = null,
)


@Entity
@Table(name = "authorities", uniqueConstraints = [UniqueConstraint(columnNames = arrayOf("username", "authority"))])
data class Authority(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    var username: String? = null,
    var authority: String? = null
)
