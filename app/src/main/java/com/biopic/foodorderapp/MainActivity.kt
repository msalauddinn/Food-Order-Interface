package com.biopic.foodorderapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.biopic.foodorderapp.ui.theme.FoodOrderAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodOrderAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                            .padding(innerPadding)
                    ) {
                        FoodOrder()
                    }
                }
            }
        }
    }
}

@Composable
fun FoodOrder() {

    val customerName = remember {
        mutableStateOf("")
    }

    val phoneNumber = remember {
        mutableStateOf("")
    }

    val foodList = arrayOf("Pizza", "Burger", "Sandwich")

    val selectedFood = remember {
        mutableIntStateOf(0)
    }

    val imageList = arrayOf(R.drawable.pizza, R.drawable.burger, R.drawable.sandwitch)

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = "Food Order",
            color = Color(0xFF8B2323),
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )
        Spacer(modifier = Modifier.size(10.dp))
        FoodImage(imageList, selectedFood)
        Spacer(modifier = Modifier.size(7.dp))
        UserDetails(customerName, phoneNumber)
        ChooseFood(foodList, selectedFood)
        Spacer(modifier = Modifier.size(2.dp))
    }
}

@Composable
fun FoodImage(imageList : Array<Int>, selectedFood : MutableIntState) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .fillMaxHeight(0.15f)
                .background(
                    Color(0x66FDD0C7),
                    shape = RoundedCornerShape(11.dp)
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(imageList[selectedFood.intValue]),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxHeight(0.9f)
                    .graphicsLayer(
                        rotationX = 40f
                    ),
                contentScale = ContentScale.Fit,
                alignment = Alignment.Center,
            )
        }
    }
}

@Composable
fun UserDetails(customerName: MutableState<String>, phoneNumber: MutableState<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.8f),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Customer name",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp,
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = customerName.value,
                onValueChange = {
                    customerName.value = it
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null
                    )
                },
                placeholder = {
                    Text(
                        text = "Enter your name",
                        color = Color.LightGray,
                        fontSize = 15.sp
                    )
                },
                modifier = Modifier.fillMaxWidth(0.9f),
                singleLine = true,
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,

                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )
        }
        Text(
            text = "Phone Number",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = phoneNumber.value,
                onValueChange = {
                    phoneNumber.value = it
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = null
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,

                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                ),
                placeholder = {
                    Text(
                        text = "Enter your phone number",
                        color = Color.LightGray,
                        fontSize = 15.sp
                    )
                },
                modifier = Modifier.fillMaxWidth(0.9f),
                shape = RoundedCornerShape(8.dp),
                singleLine = true
            )
        }
    }
}

@Composable
fun ChooseFood(foodList : Array<String>, selectedFood : MutableIntState) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.8f)
    ) {
        Text(
            text = "Choose Food",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0x66C4FFC4),
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            foodList.forEachIndexed { idxFood, foodName ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable {
                            selectedFood.intValue = idxFood
                        }
                ) {
                    CompositionLocalProvider(
                        LocalMinimumInteractiveComponentSize provides Dp.Unspecified
                    ) {
                        RadioButton(
                            selected = selectedFood.intValue == idxFood,
                            onClick = {
                                selectedFood.intValue = idxFood
                            },
                            modifier = Modifier.scale(0.7f),
                            colors = RadioButtonDefaults.colors(Color(0xFF563D86))
                        )
                    }
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(
                        text = foodName,
                        color = Color.Black,
                        fontSize = 13.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FoodOrderPreview() {
    FoodOrderAppTheme {
        FoodOrder()
    }
}