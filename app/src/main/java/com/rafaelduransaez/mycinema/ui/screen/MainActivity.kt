package com.rafaelduransaez.mycinema.ui.screen

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.rafaelduransaez.mycinema.databinding.MainActivityLayoutBinding
import com.rafaelduransaez.mycinema.extensions.toast
import com.rafaelduransaez.mycinema.framework.PermissionManager
import com.rafaelduransaez.mycinema.ui.adapters.MoviesAdapter
import com.rafaelduransaez.mycinema.ui.theme.MyCinemaTheme
import com.rafaelduransaez.mycinema.ui.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    private val permissionManager = PermissionManager(this, Manifest.permission.ACCESS_FINE_LOCATION)


    private val viewModel: MoviesViewModel by viewModels()
    private val adapter = MoviesAdapter {
        toast("Clicked movie: ${it.id}: ${it.title}")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = MainActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycler.adapter = adapter



        lifecycleScope.launch {
            permissionManager.request()
            viewModel.onLocationPermissionRequested()
        }


        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    adapter.submitList(it.movies)
                    toast("Movies loaded: ${it.movies.size}", Toast.LENGTH_SHORT)
                }
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