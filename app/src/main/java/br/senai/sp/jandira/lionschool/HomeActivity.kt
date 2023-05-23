package br.senai.sp.jandira.lionschool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import br.senai.sp.jandira.lionschool.components.TopShape
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme

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
        Image(painter = painterResource(id = R.drawable.students), contentDescription = "alunos")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    LionSchoolTheme {
        HomeScreen()
    }
}