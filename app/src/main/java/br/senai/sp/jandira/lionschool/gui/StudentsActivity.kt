package br.senai.sp.jandira.lionschool.gui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.R
import br.senai.sp.jandira.lionschool.components.TopShape
import br.senai.sp.jandira.lionschool.gui.ui.theme.LionSchoolTheme
import br.senai.sp.jandira.lionschool.model.Alunos
import br.senai.sp.jandira.lionschool.model.AlunosList
import br.senai.sp.jandira.lionschool.model.Cursos
import br.senai.sp.jandira.lionschool.model.CursosList
import br.senai.sp.jandira.lionschool.service.RetrofityFactory
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StudentsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    StudentsApp()
                }
            }
        }
    }
}

@Composable
fun StudentsApp() {

    var alunos by remember {
        mutableStateOf(listOf<Alunos>())
    }

    Column(modifier = Modifier.fillMaxSize()) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Image(
                painter = painterResource(id = R.drawable.menu), contentDescription = "menu",
                modifier = Modifier
                    .width(32.dp)
                    .height(32.dp)
                    .padding(4.dp)
            )
            Spacer(modifier = Modifier.width(250.dp))
            TopShape()
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(text = stringResource(id = R.string.status), fontSize = 18.sp)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center

        ) {

            Button(modifier = Modifier
                .width(160.dp)
                .height(38.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(Color(217, 218, 219)),
                onClick = { /*TODO*/ }

            ) {
                Text(
                    text = stringResource(id = R.string.studying),
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp
                )
            }
            Spacer(modifier = Modifier.width(35.dp))

            Button(modifier = Modifier
                .width(162.dp)
                .height(38.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(Color(217, 218, 219)),
                onClick = { /*TODO*/ }

            ) {
                Text(
                    text = stringResource(id = R.string.finished),
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Column(modifier = Modifier.fillMaxWidth()) {


            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {


                val call = RetrofityFactory().getAlunosService().getAlunosCurso("ds")

                call.enqueue(object : Callback<AlunosList> {
                    override fun onResponse(
                        call: Call<AlunosList>,
                        response: Response<AlunosList>
                    ) {
                        //receber os resultados do corpo da resposta
                        //variavel de estado
                        //body pode vir nulo (nao é adequado!!)
                        alunos = response.body()!!.alunos

                    }

                    override fun onFailure(call: Call<AlunosList>, t: Throwable) {
                        Log.i(
                            "ds2m",
                            "onFailure: ${t.message}"
                        )
                    }

                })

                items(alunos) { aluno ->
                    val backgroundColor = when (aluno.status) {
                        "Cursando" -> Color(253, 219, 88)
                        "Finalizado" -> Color(41, 88, 181)
                        else -> Color.White
                    }

                    Card(
                        modifier = Modifier
                            .height(330.dp)
                            .width(250.dp)
                            .padding(15.dp),
                        elevation = 20.dp,
                        backgroundColor = backgroundColor
                    ) {
                        AsyncImage(model = aluno.foto, contentDescription = "foto")
                        Text(
                            text = aluno.nome,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                    }
                }

            }
        }


    }




}
    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    fun DefaultPreview3() {
        LionSchoolTheme {
            StudentsApp()
        }
    }

