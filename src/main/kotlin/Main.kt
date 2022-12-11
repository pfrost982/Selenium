import ads_api.AdsAPI
import ads_api.response.AdsResponse
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "http://local.adspower.net:50325/api/v1/"

fun main() {
    val api = getAPI()
    api.getProfile("j4fy3ir").enqueue(object: Callback<AdsResponse>{
        override fun onResponse(call: Call<AdsResponse>, response: Response<AdsResponse>) {
            if (response.isSuccessful) {
                response.body()?.let {
                    System.setProperty("webdriver.chrome.driver", it.data.webdriver)
                    val options = ChromeOptions()
                    options.setExperimentalOption("debuggerAddress", it.data.ws.selenium)
                    val driver = ChromeDriver(options)
                    driver.get("https://mail.ru")
                }
            }
        }

        override fun onFailure(call: Call<AdsResponse>, t: Throwable) {
            println("Network error: $t")
        }
    })
}

fun getAPI(): AdsAPI {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(AdsAPI::class.java)
}