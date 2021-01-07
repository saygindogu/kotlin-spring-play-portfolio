package info.saygindogu.hobby.portfolio

import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
interface UserRepository : CrudRepository<User, Long> {
    fun findByEmail(email: String): User?
    fun findByUsername(username: String): User?
}

@RepositoryRestResource(collectionResourceRel = "authorities", path = "authorities")
interface AuthorityRepository : CrudRepository<Authority, Long> {
    fun findByUsername(username: String?): Authority?
    fun findByUsernameAndAuthority(username: String, authority:String): Authority?
}



