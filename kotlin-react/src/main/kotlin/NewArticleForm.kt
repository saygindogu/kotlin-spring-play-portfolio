package info.saygindogu.kotlin.react

import kotlinx.browser.window
import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onSubmitFunction

import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import org.w3c.xhr.FormData
import react.*
import react.dom.form
import react.dom.input
import react.dom.label


external interface NewArticleFormState : RState {
    var content: String
    var headline: String
    var title: String
}


class NewArticleForm : RComponent<RProps, NewArticleFormState>() {
    override fun NewArticleFormState.init() {
        content = "Content"
        headline = "Headline"
        title = "Title"
    }

    fun onTitleChange(event: Event) {
        val target = event.target as HTMLInputElement
        setState {
            title = target.value
        }
    }

    fun onHeadlineChange(event: Event) {
        val target = event.target as HTMLInputElement
        setState {
            headline = target.value
        }
    }

    fun onContentChange(event: Event) {
        val target = event.target as HTMLInputElement
        setState {
            content = target.value
        }
    }

    fun handleSubmit(event: Event) {
        event.preventDefault();
        doRequest<Article>("api/articles/", "post", state, null)
        window.location.href = "localhost:8080"
    }

    override fun RBuilder.render() {

        form {
            attrs {
                onSubmitFunction = {
                    handleSubmit(it)
                }
            }
            label {
                +"Title"
                input {
                    attrs.type = InputType.text
                    attrs.value = state.title
                    attrs {
                        onChangeFunction = {
                            onTitleChange(it)
                        }
                    }
                }
            }
            label {
                +"Headline"
                input {
                    attrs.type = InputType.text
                    attrs.value = state.headline
                    attrs {
                        onChangeFunction = {
                            onHeadlineChange(it)
                        }
                    }
                }
            }
            label {
                +"Content"
                input {
                    attrs.type = InputType.text
                    attrs.value = state.content
                    attrs {
                        onChangeFunction = {
                            onContentChange(it)
                        }
                    }
                }
            }
            input {
                attrs.type = InputType.submit
                attrs.value = "Submit"
            }
        }

    }
}

fun RBuilder.newArticleForm(handler: RProps.() -> Unit): ReactElement {
    return child(NewArticleForm::class) {
        this.attrs(handler)
    }
}