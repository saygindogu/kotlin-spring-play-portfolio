package info.saygindogu.hobby.portfolio.portfolio

import info.saygindogu.hobby.portfolio.User
import info.saygindogu.hobby.portfolio.toSlug
import java.time.LocalDateTime
import javax.persistence.*


@Entity
data class File(
    @Id
    @GeneratedValue
    var id: Long? = null,
    var title: String = "",
    var slug: String = title.toSlug(),
    var addedAt: LocalDateTime = LocalDateTime.now(),
    @ManyToOne var owner: User = User(
        email = "Hebele",
        firstName = "Hobili",
        lastName = "Zop",
        description = "Koyt?"
    ),
    @Lob
    @Column(name = "file", columnDefinition = "BLOB")
    private var photo: ByteArray
)