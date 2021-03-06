/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.android.marsrealestate.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

/**
 * Data class to represent properties for sale.
 * @param id: A unique ID for the properties
 * @param imgSrcUrl: the URL of the source image.
 * A @Json annotation remaps the img_src JSON field to it, because img_src is going to look weird
 * among all the camelCase stuff.
 * @param type: The type of properties (e.g. apartment, house)
 * @param price: The price of the properties. All JSON numbers can be stored as Kotlin doubles.
 */
@Parcelize
data class MarsProperty(
        val id: String,
        @Json(name = "img_src") val imgSrcUrl: String,
        val type: String,
        val price: Double) : Parcelable {
    val isRental get() = type == "rent"
}
