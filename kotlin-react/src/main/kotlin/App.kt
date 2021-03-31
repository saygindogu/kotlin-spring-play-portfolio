package info.saygindogu.kotlin.react

import kotlinx.coroutines.async
import react.*
import react.dom.*
import kotlinx.browser.window
import kotlinx.coroutines.*
import org.w3c.fetch.Response
import kotlin.js.Date
import kotlin.js.Json


suspend fun fetchArticle(id: Int): Article? {
    val response = window.fetch("http://localhost:8080/api/articles/$id")
        .await()
    val forOFor: Short = 404
    if (response.unsafeCast<Response>().status == forOFor) {
        return null
    }
    val json = response.json()
        .await().unsafeCast<Json>()
    return get_article_from_json(json)
}

private suspend fun get_article_from_json(json: Json) = Article(
    author =
    window.fetch(json["_links"].unsafeCast<Json>()["author"].unsafeCast<Json>()["href"]).await()
        .json()
        .await().unsafeCast<User>(),
    content = json["content"].unsafeCast<String>(),
    title = json["title"].unsafeCast<String>(),
    headline = json["headline"].unsafeCast<String>(),
    slug = json["headline"].unsafeCast<String>(),
    id = json["id"].unsafeCast<Int>(),
    addedAt = json["addedAt"].unsafeCast<Date>()
)

suspend fun fetchArticles(): List<Article> = coroutineScope {
    val response = window.fetch("http://localhost:8080/api/articles/all").await()
    val json = response.json()
        .await().unsafeCast<Json>()
    return json['articles'].unsafeCast<List<Json>>().map{ get_article_from_json(it) }
}

external interface AppState : RState {
    var currentArticle: Article?
    var unreadArticles: List<Article>
    var readArticles: List<Article>
}

class App : RComponent<RProps, AppState>() {
    override fun AppState.init() {
        unreadArticles = listOf()
        readArticles = listOf()

        val mainScope = MainScope()
        mainScope.launch {
            val articles = fetchArticles()
            setState {
                unreadArticles = articles
            }
        }
    }

    override fun RBuilder.render() {
        h1 {
            +"Saygin's Article Library"
        }
        newArticleForm {}
        div {
            h3 {
                +"Articles to read"
            }
            articleList {
                articles = state.unreadArticles
                selectedArticle = state.currentArticle
                onSelectArticle = { Article ->
                    setState {
                        currentArticle = Article
                    }
                }
            }

            h3 {
                +" Articles read"
            }
            articleList {
                articles = state.readArticles
                selectedArticle = state.currentArticle
                onSelectArticle = { Article ->
                    setState {
                        currentArticle = Article
                    }
                }
            }
        }
    }
}