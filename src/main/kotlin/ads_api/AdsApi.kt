package ads_api

import ads_api.response.AdsResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface AdsApi {
    @GET("browser/start")
    suspend fun openProfile(
        @Query("user_id") userId: String,
        @Query("open_tabs") openTabs: Int = 0,
        @Query("ip_tab") ipTab: Int = 1,
        @Query("headless") headless: Int = 0
    ): AdsResponse

    @GET("browser/stop")
    suspend fun closeProfile(
        @Query("user_id") userId: String,
    ): AdsResponse

    @GET("browser/active")
    suspend fun checkOpenStatusProfile(
        @Query("user_id") userId: String,
    ): AdsResponse
}