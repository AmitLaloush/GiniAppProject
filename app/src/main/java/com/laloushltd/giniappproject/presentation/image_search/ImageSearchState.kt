package com.laloushltd.giniappproject.presentation.image_search

import com.laloushltd.giniappproject.domain.model.ImageData


data class ImageSearchState(
    val isLoading: Boolean = false,
    val error: String? = "",
    val images: List<ImageData> = mutableListOf()
)
