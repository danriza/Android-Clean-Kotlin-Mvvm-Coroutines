package com.codingassignemnt.app.framework.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codingassignemnt.app.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, FragmentNewsList())
            .commit()
    }

}



















