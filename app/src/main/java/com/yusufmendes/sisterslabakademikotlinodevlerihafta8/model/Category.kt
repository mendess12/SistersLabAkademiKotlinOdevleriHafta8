package com.yusufmendes.sisterslabakademikotlinodevlerihafta8.model

import android.os.Parcelable
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.parcelize.Parcelize


@Parcelize
@IgnoreExtraProperties
data class Category(val categoryName: String? = "") :
    Parcelable
