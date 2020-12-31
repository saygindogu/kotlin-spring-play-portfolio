package info.saygindogu.hobby.portfolio.blog

import info.saygindogu.hobby.portfolio.blog.Article
import org.springframework.context.annotation.Configuration
import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer

@Configuration
open class RestConfiguration : RepositoryRestConfigurer {
    override fun configureRepositoryRestConfiguration(config: RepositoryRestConfiguration?) {
        config?.exposeIdsFor(Article::class.java)
        config?.setBasePath("/api")
    }
}