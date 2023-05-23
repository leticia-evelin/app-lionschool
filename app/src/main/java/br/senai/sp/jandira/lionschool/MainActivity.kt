package br.senai.sp.jandira.lionschool

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LoginApp()
                }
            }
        }
    }
}

@Composable
fun LoginApp() {

    val context = LocalContext.current.applicationContext
    val context2 = LocalContext.current


    Column(modifier = Modifier
        .fillMaxWidth()
        .background(Color(51, 71, 176))) {

        Row(modifier = Modifier.padding(32.dp)) {
            Text(text = stringResource(id = R.string.app_name),
                fontSize = 32.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
        Card(modifier = Modifier.width(415.dp).height(2.dp) ,shape = RoundedCornerShape(topEnd = 2.dp), backgroundColor = Color(242, 180, 65)) {
        }
        Spacer(modifier = Modifier.height(95.dp))
        Row() {
            Image(painter = painterResource(id = R.drawable.logo),
                alignment = Alignment.Center,
                contentDescription = "logo lion",
                modifier = Modifier
                    .width(400.dp)
                    .height(170.dp)
            )
        }
        Spacer(modifier = Modifier.height(140.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(onClick = { val openNext = Intent(context2, HomeActivity::class.java)
                             context2.startActivity(openNext)},
                colors = ButtonDefaults.buttonColors(Color(242, 180, 65)),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .width(220.dp)
                    .height(42.dp)


            ) {
                Row() {
                    Text(text = stringResource(id = R.string.get_started),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(51, 71, 176),
                        modifier = Modifier.clickable {

                        }

                    )
                }
        }


        }

    }



}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    LionSchoolTheme {
        LoginApp()
    }
}