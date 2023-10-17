package com.example.artspace.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ArtSpace (
    @StringRes val title: Int,
    @StringRes val artist: Int,
    @StringRes val year: Int,
    @DrawableRes val artImage: Int
)