package ads_api

import ads_api.response.AdsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AdsAPI {
    @GET("browser/start")
    fun getProfile(
        @Query("user_id") id: String
    ): Call<AdsResponse>
}