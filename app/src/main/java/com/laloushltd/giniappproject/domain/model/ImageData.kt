package com.laloushltd.giniappproject.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImageData(
    @PrimaryKey val id: Int?,
    val url: String?,
    val likes: Int?
)

