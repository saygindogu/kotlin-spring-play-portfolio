package info.saygindogu.kotlin.react

import kotlinx.html.js.onClickFunction
import react.*
import react.dom.*


external interface ArticleListProps: RProps {
    var articles: List<Article>
    var selectedArticle: Article?
    var onSelectArticle: (Article) -> Unit
}

class ArticleList: RComponent<ArticleListProps, RState>() {
    override fun RBuilder.render() {
        for (article in props.articles) {
            p {
                key = article.id.toString()
                attrs {
                    onClickFunction = {
                        props.onSelectArticle(article)
                    }
                }
                if(article == props.selectedArticle) {
                    +"▶ "
                }
                +"${article.author.firstname} ${article.author.lastname}: ${article.title}"
            }
        }
    }
}

fun RBuilder.articleList(handler: ArticleListProps.() -> Unit): ReactElement {
    return child(ArticleList::class) {
        this.attrs(handler)
    }
}