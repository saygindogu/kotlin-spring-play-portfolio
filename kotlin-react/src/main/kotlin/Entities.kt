package info.saygindogu.kotlin.react

import kotlin.js.Date

data class Article(val id: Int, val title: String, val headline: String, val author: User, val content: String, val slug: String, val addedAt: Date)
data class User(val id: Int, val login: String, val firstname: String, val lastname: String, val description: String)