package uz.kozimjon.paginationapp.api

import retrofit2.http.*
import uz.kozimjon.paginationapp.model.CharacterResponse

interface ApiService {

    @GET("character")
    suspend fun getCharacter(@Query("page") query: Int): CharacterResponse
}