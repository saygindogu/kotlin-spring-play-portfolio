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
    return Article(
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
}

suspend fun fetchArticles(): List<Article> = coroutineScope {
    (1..3).map { id ->
        async {
            fetchArticle(id)
        }
    }.awaitAll().mapNotNull { item -> item }
}

external interface AppState : RState {
    var currentArticle: Article?
    var unwatchedArticles: List<Article>
    var watchedArticles: List<Article>
}

class App : RComponent<RProps, AppState>() {
    override fun AppState.init() {
        unwatchedArticles = listOf()
        watchedArticles = listOf()

        val mainScope = MainScope()
        mainScope.launch {
            val articles = fetchArticles()
            setState {
                unwatchedArticles = articles
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
                articles = state.unwatchedArticles
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
                articles = state.watchedArticles
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