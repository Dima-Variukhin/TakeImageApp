package com.example.takeimageapp.images.data.cloud

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ImagesService {

    @Headers("Accept-Version: v1", "Authorization: Client-ID $CLIENT_ID")
    @GET("search/photos")
    suspend fun searchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int,
    ): ImagesResponse

    companion object {
        private const val CLIENT_ID = "EOP6Cf25REKfyoIMzBiOeMgEQ5tm6_UBY8VgbezIYWI"
    }
}