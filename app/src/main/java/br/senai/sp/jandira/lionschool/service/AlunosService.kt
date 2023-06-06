package br.senai.sp.jandira.lionschool.service

import br.senai.sp.jandira.lionschool.model.AlunosList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AlunosService {

    @GET("alunos")
    fun getAlunos(): Call<AlunosList>

    @GET("alunos/{curso}")
    fun getAlunosCurso(@Query("curso") curso: String): Call<AlunosList>

    @GET("alunos/rds/todos")
    fun getAlunoCurso(): Call<AlunosList>
}