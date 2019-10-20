import com.adamstyrc.network.NetworkModule
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

fun main() {
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()
    val githubApi = NetworkModule().provideGithubApi(okHttpClient)
    runBlocking {
        val userDescription = githubApi.getUserDescription()
        println(userDescription.body())
    }
}