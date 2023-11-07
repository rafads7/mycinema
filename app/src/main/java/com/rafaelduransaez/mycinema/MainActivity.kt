package com.rafaelduransaez.mycinema

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.rafaelduransaez.framework.api.RetrofitClient
import com.rafaelduransaez.mycinema.constants.Utils
import com.rafaelduransaez.mycinema.extensions.toast
import com.rafaelduransaez.mycinema.ui.theme.MyCinemaTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {

    private val viewmodel: MoviesViewModel by lazy {
        MoviesViewModel()
    }

    /*
    private val permissionsLauncher: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            lifecycleScope.launch {
                //val location = getLocation(isGranted)

            }
        }

     */


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity_layout)

        //permissionsLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)


        lifecycleScope.launch(Dispatchers.IO) {
            val movies = RetrofitClient.service.popularMovies(Utils.api_key)
            withContext(Dispatchers.Main) {
                toast(movies.results.size.toString())
            }
        }

        //setComposable()
    }

    private fun setComposable() {
        setContent {
            MyCinemaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Rafa")
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
    MyCinemaTheme {
        Greeting("Android")
    }
}