package es.beginescarrascochristian.globofly.services

import es.beginescarrascochristian.globofly.models.Destination
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface DestinationService{

    @GET("destination")
    fun getDetinationList():Call<List<Destination>>

    @GET("destination/{id}")
    fun getDestination(@Path("id")id: Int): Call<Destination>
}