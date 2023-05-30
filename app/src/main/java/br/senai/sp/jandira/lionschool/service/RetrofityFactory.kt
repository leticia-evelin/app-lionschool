package br.senai.sp.jandira.lionschool.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofityFactory {

    private val URL_BASE = "https://teste-lion.onrender.com/v1/lion-school/"

    private val retrofityFactory = Retrofit
        .Builder()
        .baseUrl(URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getCursosService(): CursosService{
        return  retrofityFactory.create(CursosService::class.java)
    }

}