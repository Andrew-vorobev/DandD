package com.example.dandd.data.retrofit.api

import com.example.dandd.data.retrofit.model.ItemNetwork
import retrofit2.http.GET
import retrofit2.http.Path

interface ItemApi {
    @GET("/api/classes/{index}")
    suspend fun getItems(
        @Path("index") index: String
    ) : ItemNetwork
}