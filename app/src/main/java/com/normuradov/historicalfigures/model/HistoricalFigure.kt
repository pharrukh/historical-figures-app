package com.normuradov.historicalfigures.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.normuradov.historicalfigures.R

data class HistoricalFigure(
    @DrawableRes val avatarImageResourceId: Int,
    val name: String,
    val shortDescription: String,
    val longDescription: String,

    )

