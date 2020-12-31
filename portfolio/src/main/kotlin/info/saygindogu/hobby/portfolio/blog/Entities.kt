package info.saygindogu.hobby.portfolio.blog

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity

data class Article(

    var title: String = "",
    var headline: String = "",
    var content: String = "",
    @ManyToOne var author: User = User(
        login = "Hebele",
        firstname = "Hobili",
        lastname = "Zop",
        description = "Koyt?"
    ),
    var slug: String = title.toSlug(),
    var addedAt: LocalDateTime = LocalDateTime.now(),
    @Id @GeneratedValue var id: Long? = null
)


@Entity
class User(
    var login: String,
    var firstname: String,
    var lastname: String,
    var description: String? = null,
    @Id @GeneratedValue var id: Long? = null
)