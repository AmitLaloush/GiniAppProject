package com.laloushltd.giniappproject.data.remote

import com.laloushltd.giniappproject.data.remote.dto.SearchResults
import retrofit2.http.GET

interface PixaBayApi {
    @GET("?key=13398314-67b0a9023aca061e2950dbb5a&q=yellow+flowers&image_type=photo")
    suspend fun searchImages(): SearchResults
}