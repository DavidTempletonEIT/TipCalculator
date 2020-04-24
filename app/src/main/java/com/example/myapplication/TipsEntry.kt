package com.example.myapplication

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal
@Parcelize
data class TipsEntry (
    var amount : BigDecimal,
    var percent : BigDecimal,
    var tips : BigDecimal,
    var total : BigDecimal):Parcelable



