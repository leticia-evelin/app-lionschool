package br.senai.sp.jandira.lionschool.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.lionschool.R


@Composable
fun TopShape(){
    Card(modifier = Modifier
        .width(120.dp)
        .height(35.dp),
        shape = RoundedCornerShape(bottomStart = 16.dp),
        backgroundColor = Color(51, 71, 176)
    ) {

        Text(text = stringResource(id = R.string.app_name),
             color = Color.White,
             textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun TopShapePreview(){
    TopShape()
}