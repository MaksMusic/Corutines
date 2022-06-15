package com.music.corutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.widget.ThemedSpinnerAdapter
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.music.corutines.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {


    var time: Int = 0
    var time2: Int = 0
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn5.setOnClickListener() {

            binding.TextViewtime.text = if (time > time2) time.toString()+ " - potok 1" else time2.toString() + " - potok 4"
        }


        binding.btn1.setOnClickListener() {
            lifecycleScope.launch(Dispatchers.IO) {
                for (n in 0..500) {
                    delay(1000)
                    time = n
                }
            }
        }

        binding.btn2.setOnClickListener() {
            lifecycleScope.launch(Dispatchers.IO) {
                withContext(Dispatchers.Main) {
                    for (n in 0..500) {
                        delay(1000)
                        binding.TextViewtime.text = n.toString()
                    }
                }
            }
        }

        binding.btn3.setOnClickListener() {
            lifecycleScope.launch {
                lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    for (n in 0..500) {
                        delay(1000)
                        binding.TextViewtime.text = n.toString()
                    }

                }
            }

        }

        binding.btn4.setOnClickListener {

            lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    for (n in 0..500) {
                        delay(1000)
                        time2 = n
                    }
                }

            }
        }


    }

//    fun timeOn(view: View) {
//        for (n in 0..60) {
//            Handler().postDelayed({
//                binding.TextViewtime.text =
//                    (binding.TextViewtime.text.toString().toInt() + 1).toString()
//            }, 1000)
//        }
//    }
}