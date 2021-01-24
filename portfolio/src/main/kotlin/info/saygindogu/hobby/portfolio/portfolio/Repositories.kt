package info.saygindogu.hobby.portfolio.portfolio

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(collectionResourceRel = "files", path = "files")
interface FileRepository : PagingAndSortingRepository<File, Long> {
    fun findBySlug(slug: String): File?
    fun findAllByOrderByAddedAtDesc(): Iterable<File>
}