package com.laloushltd.giniappproject.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SearchResults (

    @SerializedName("total")
    val total     : Int?            = null,
    @SerializedName("totalHits")
    val totalHits : Int?            = null,
    @SerializedName("hits") var hits      : ArrayList<Hit> = arrayListOf()

)