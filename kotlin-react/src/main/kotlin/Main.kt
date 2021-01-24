package info.saygindogu.kotlin.react

import react.dom.*
import kotlinx.browser.document
import kotlin.js.Date

fun main() {
    render(document.getElementById("blog-application")) {
        child(App::class) {}
    }
}