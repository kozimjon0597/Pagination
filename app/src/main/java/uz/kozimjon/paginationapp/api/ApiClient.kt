package uz.kozimjon.paginationapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.kozimjon.paginationapp.utils.Constants

object ApiClient {

    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService::class.java)
    }
}