package br.senai.sp.jandira.lionschool.service

import br.senai.sp.jandira.lionschool.model.AlunosList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AlunosService {

    @GET("alunos")
    fun getAlunos(): Call<AlunosList>

    @GET("alunos/{curso}")
    fun getAlunosssssCurso(@Query("curso") curso: String): Call<AlunosList>

    @GET("alunos/{curso}/todos")
    fun getAlunosCurso(@Path("curso") curso: String): Call<AlunosList>

}