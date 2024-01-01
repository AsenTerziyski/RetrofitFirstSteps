package com.example.retrofitget

import android.net.http.HttpException
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.retrofitget.ui.theme.RetrofitGetTheme
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class MainActivity : ComponentActivity() {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            try {
                val response: Response<List<Todo>> = RetroFitInstance.testApi.getTodos()
                if (response.isSuccessful && response.body() != null) {
                    val resultList = response.body()!!
                    var counter = 0
                    for (todo in resultList) {
                        counter++
                        if (counter == 11) {
                            break
                        }
                        Log.d("TAG101", counter.toString() + "-" + todo.title + "-" + todo.completed)
                    }
                }
            } catch (e: IOException) {
                Log.d("TAG101", e.toString())
            } catch (e: HttpException) {
                Log.d("TAG101", e.toString())
            }
        }

        setContent {
            RetrofitGetTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }

        }

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RetrofitGetTheme {
        Greeting("Android")
    }
}