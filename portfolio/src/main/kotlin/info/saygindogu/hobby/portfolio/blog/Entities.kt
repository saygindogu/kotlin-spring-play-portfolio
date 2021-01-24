package info.saygindogu.hobby.portfolio.blog

import info.saygindogu.hobby.portfolio.User
import info.saygindogu.hobby.portfolio.toSlug
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
    @ManyToOne var author: User = User(),
    var slug: String = title.toSlug(),
    var addedAt: LocalDateTime = LocalDateTime.now(),
    @Id @GeneratedValue var id: Long? = null
)