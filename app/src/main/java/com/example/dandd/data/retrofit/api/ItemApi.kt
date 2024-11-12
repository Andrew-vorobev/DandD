package com.example.dandd.data.retrofit.api

import com.example.dandd.data.retrofit.model.itemInfo.ItemNetwork
import com.example.dandd.data.retrofit.model.classes.ClassesNetwork
import retrofit2.http.GET
import retrofit2.http.Path

interface ItemApi {
    @GET("/api/classes/{index}")
    suspend fun getClass(
        @Path("index") index: String
    ) : ItemNetwork

    @GET("/api/classes")
    suspend fun getClasses(
    ): ClassesNetwork
}