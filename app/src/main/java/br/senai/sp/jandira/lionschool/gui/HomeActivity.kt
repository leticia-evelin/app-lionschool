package br.senai.sp.jandira.lionschool.gui

import android.content.Intent
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.R
import br.senai.sp.jandira.lionschool.components.TopShape
import br.senai.sp.jandira.lionschool.model.CursosList
import br.senai.sp.jandira.lionschool.service.RetrofityFactory
import br.senai.sp.jandira.lionschool.gui.theme.LionSchoolTheme
import br.senai.sp.jandira.lionschool.model.Cursos
import br.senai.sp.jandira.lionschool.service.AlunosService
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.compose.runtime.remember as remember

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    HomeScreen()
                }
            }
        }
    }
}

@Composable
fun HomeScreen() {
    val context = LocalContext.current

    var cursos by remember {
        mutableStateOf(listOf<Cursos>())
    }



    Column(modifier = Modifier.fillMaxSize()) {

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Image(painter = painterResource(id = R.drawable.menu), contentDescription = "menu",
            modifier = Modifier
                .width(32.dp)
                .height(32.dp)
                .padding(4.dp))
            Spacer(modifier = Modifier.width(250.dp))
            TopShape()
        }
            Spacer(modifier = Modifier.height(50.dp))
        Row() {
            Text(text = stringResource(id = R.string.choose_course),
                fontSize = 32.sp,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center) {

            Image(painter = painterResource(id = R.drawable.students), contentDescription = "alunos",
                modifier = Modifier
                    .size(250.dp)
                    .padding(15.dp))
        }


        Spacer(modifier = Modifier.height(15.dp))

        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

            Card(modifier = Modifier
                .fillMaxWidth()
                .height(215.dp)
                .padding(20.dp)
               , elevation = 15.dp,
                backgroundColor = Color.White,
                shape = RoundedCornerShape(20.dp)){

                LazyColumn(modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                ){

                    val call = RetrofityFactory().getCursosService().getCursos()

                    call.enqueue(object : Callback<CursosList>{
                        override fun onResponse(
                            call: Call<CursosList>,
                            response: Response<CursosList>
                        ) {
                            //receber os resultados do corpo da resposta
//                            //variavel de estado
//                            //body pode vir nulo (nao é adequado!!)
                            cursos = response.body()!!.cursos
//
                       }

                        override fun onFailure(call: Call<CursosList>, t: Throwable) {
                            Log.i(
                                "ds2m",
                                "onFailure: ${t.message}"
                            )
                        }

                    })


                    items(cursos) {

                            Button(
                                modifier = Modifier
                                    .width(170.dp)
                                    .height(50.dp),
                                shape = RoundedCornerShape(12.dp),
                                onClick = {
                                    val openNext = Intent(context, StudentsActivity::class.java)
                                    context.startActivity(openNext)
                                },
                                colors = ButtonDefaults.buttonColors(Color(51, 71, 176))
                            ) {
                                AsyncImage(model = it.icone, contentDescription = "image")
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(text = it.sigla, fontSize = 20.sp, color = Color.White)
                            }
                        }
                    }
                }




//                    val call = RetrofityFactory().getCursosService().getCursos()
//                    val call = AlunosService.getAlunoCursoByUrl("ds/todos")
//
//                    call.enqueue(object : Callback<CursosList>{
//                        override fun onResponse(
//                            call: Call<CursosList>,
//                            response: Response<CursosList>
//                        ) {
//                            //receber os resultados do corpo da resposta
//                            //variavel de estado
//                            //body pode vir nulo (nao é adequado!!)
//                            cursos = response.body()!!.cursos
//
//                        }
//
//                        override fun onFailure(call: Call<CursosList>, t: Throwable) {
//                            Log.i(
//                                "ds2m",
//                                "onFailure: ${t.message}"
//                            )
//                        }
//
//                    })

//                    items(cursos){
//
//                        Button(modifier = Modifier
//                            .width(170.dp)
//                            .height(50.dp),
//                            shape = RoundedCornerShape(12.dp),
//                            onClick = { val openNext = Intent(context, StudentsActivity::class.java)
//                                context.startActivity(openNext) },
//
//                            colors = ButtonDefaults.buttonColors(Color(51, 71, 176),)
//                        ) {
//                            AsyncImage(model = it.icone, contentDescription = "image")
//                            Spacer(modifier = Modifier.width(5.dp))
//                            Text(text = it.sigla, fontSize = 20.sp, color = Color.White)
//                        }
//
//
//                    }
//                }
//        }

        }
    }


    }



@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    LionSchoolTheme {
        HomeScreen()
    }
}