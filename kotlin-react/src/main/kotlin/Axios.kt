package info.saygindogu.kotlin.react

/**
 * An example of using axios to fetch remote data by Scott_Huang@qq.com (Zhiliang.Huang@gmail.com)
 *
 * This example queries the database of ZIP codes at http://ziptasticapi.com and displays the results.
 */

import kotlinext.js.*
import kotlinx.html.*
import kotlinx.html.js.*
import org.w3c.dom.*
import react.*
import react.dom.*
import kotlin.js.*

@JsModule("axios")
@JsNonModule
external fun <T> axios(config: AxiosConfigSettings): Promise<AxiosResponse<T>>

// Type definition
external interface AxiosConfigSettings {
    var url: String
    var method: String
    var baseUrl: String
    var timeout: Number
    var data: dynamic
    var transferRequest: dynamic
    var transferResponse: dynamic
    var headers: dynamic
    var params: dynamic
    var withCredentials: Boolean
    var adapter: dynamic
    var auth: dynamic
    var responseType: String
    var xsrfCookieName: String
    var xsrfHeaderName: String
    var onUploadProgress: dynamic
    var onDownloadProgress: dynamic
    var maxContentLength: Number
    var validateStatus: (Number) -> Boolean
    var maxRedirects: Number
    var httpAgent: dynamic
    var httpsAgent: dynamic
    var proxy: dynamic
    var cancelToken: dynamic
}

external interface AxiosResponse<T> {
    val data: T
    val status: Number
    val statusText: String
    val headers: dynamic
    val config: AxiosConfigSettings
}

fun <T> doRequest(path: String, httpMethod: String, deyta: dynamic = "", paramz: dynamic): T? {
    val config: AxiosConfigSettings = jsObject {
        url = "http://localhost:8080/${path}"
        timeout = 3000
        method = httpMethod
        params = paramz
        data = deyta
    }
    var data: T? = null
    axios<T>(config).then { res -> data = res.data }
    return data
}