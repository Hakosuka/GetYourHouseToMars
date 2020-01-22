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

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://mars.udacity.com/"

/**
 * Allows the user to filter properties.
 * @param value: The value appended to
 */
enum class MarsApiFilter(val value: String) {
    SHOW_RENT("rent"), SHOW_BUY("buy"), SHOW_ALL("all")
}
private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
//Use Retrofit Builder with a Moshi converter and a Call adapter with our Moshi object.
private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .build()

interface MarsApiService {
    /**
     * Retrieves the JSON data from the server. In this case, it will hit the /realestate endpoint.
     * @param type: ?filter=$type, where $type is one of the values of the MarsApiFilters
     */
    @GET("realestate") //specifies the endpoint for the JSON real estate status
    fun getProperties(@Query("filter") type: String):
            Deferred<List<MarsProperty>> //Deferred types don't block threads
}
/**
 *  Create the MarsApi object using Retrofit to implement the MarsApiService, and expose it to the
 *  rest of the app.
 */

object MarsApi {
    val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
}