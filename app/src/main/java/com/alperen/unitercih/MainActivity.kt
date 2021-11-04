package com.alperen.unitercih

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.alperen.unitercih.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        with(binding) {
            btnMale.isChecked = true

            etName.addTextChangedListener {
                if (it.isNullOrEmpty()) {
                    nameLayout.error = "Lütfen isminizi giriniz"
                    nameLayout.isErrorEnabled = true
                } else {
                    nameLayout.isErrorEnabled = false
                }
            }

            btnStart.setOnClickListener {
            }
        }
    }

}