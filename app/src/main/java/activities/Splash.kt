package activities

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hotel.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolbar(title: String){
//    TopAppBar(
//        title= {
//            Text(text= title, color= MaterialTheme.colorScheme.onPrimary)
//        },
//        colors= TopAppBarDefaults.topAppBarColors(
//        containerColor = MaterialTheme.colorScheme.primary,
//        titleContentColor =MaterialTheme.colorScheme.onPrimary
//        )
//        //you can add navigation icon, actions here
//    )
}
@Composable
fun LoginScreen(
    onLoginClick:(String, String) -> Unit,
    onForgotPasswordClick:()->Unit,
    onSignUpClick:()->Unit
){
    //state variables to hold the user input
    var phoneNumber by remember {mutableStateOf("") }
    var passCode by remember{mutableStateOf("")}
    var passwordVisibility by remember {mutableStateOf(false)}

    Scaffold (
        topBar= { AppToolbar(title="Login") }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                )
        Image(
            painter= painterResource(id= R.drawable.background),
            contentDescription = "BackgroundImage",
            modifier = Modifier.fillMaxSize (),
            contentScale = ContentScale.FillBounds
        )
        Box(
            modifier= Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
        )
         {
            Spacer(modifier = Modifier.height(80.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.85F)
                    .align(Alignment.Center)
                    .offset(y = (-20).dp),
                shape = RoundedCornerShape(30.dp),
             colors= CardDefaults.cardColors(containerColor = Color.White)

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                        .background(Color.White, RoundedCornerShape(30.dp)),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Text(
                        text = stringResource(id = R.string.login),
                        fontWeight = FontWeight.Bold,
                        fontSize = 36.sp,
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    //phone input field
                    OutlinedTextField(
                        value = phoneNumber,
                        onValueChange = { phoneNumber = it },
                        label = { Text(stringResource(id = R.string.phoneNumber)) },
                        leadingIcon = { Icon(Icons.Default.Call, contentDescription = "Registered Phone") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)),
//                        colors = TextFieldDefaults.outlinedTextFieldColors(
//                            textColor = Color.Black,
//                            focusedBorderColor = MaterialTheme.colorScheme.primary,
//                            unfocusedBorderColor = Color.Gray,
//                            cursorColor = MaterialTheme.colorScheme.primary,
//                            placeholderColor = Color.Black,
//                            leadingIconColor = Color.Black
//                        ),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                    //passwordInputField
                    OutlinedTextField(
                        value = passCode,
                        onValueChange = { passCode = it },
                        label = { Text(stringResource(id = R.string.password)) },
                        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Lock Icon") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
//                        trailingIcon = {
//                            val image = if (passwordVisibility) {
//                                Icons.Filled.Visibility
//                            }else{ Icons.Filled.VisibilityOff}
//                            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
//                                Icon(
//                                    imageVector = image,
//                                    contentDescription = "Toggle password visibility"
//                                )
//                            }
//                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)),
//                        colors = TextFieldDefaults.outlinedTextFieldColors(
//                            textColor = Color.Black,
//                            focusedBorderColor = MaterialTheme.colorScheme.primary,
//                            unfocusedBorderColor = Color.Gray,
//                            cursorColor = MaterialTheme.colorScheme.primary,
//                            placeholderColor = Color.Black,
//                            leadingIconColor = Color.Black,
//                            trailingIconColor = Color.Black
//                        ),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    Button(
                        onClick = { onLoginClick(phoneNumber, passCode) },
                        modifier = Modifier
                            .width(300.dp)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                        shape = RoundedCornerShape(20.dp),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 5.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.login),
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))

                    //forgotPasswordText
                    Text(
                        text = stringResource(id = R.string.forgot_password),
                        fontSize = 20.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .clickable { onForgotPasswordClick }
                            .align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.weight(1f))

                    //sign up text
                    Text(
                        text = stringResource(id = R.string.don_t_have_an_account_sign_up_now),
                                color = Color.Black,
                        modifier = Modifier
                            .padding(bottom = 40.dp)
                            .clickable { onSignUpClick() }
                            .align(Alignment.CenterHorizontally)
                    )
                }
            }
        }

    }
}


@Preview(showBackground = true,name= "Login Screen Preview")
@Composable

    fun LoginScreenPreview(){

            LoginScreen(
                onLoginClick = {phoneNumber,passcode->},
                onForgotPasswordClick = {},
                onSignUpClick = {})
}