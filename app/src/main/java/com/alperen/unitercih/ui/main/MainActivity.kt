package com.alperen.unitercih.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.RadioButton
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.alperen.unitercih.R
import com.alperen.unitercih.databinding.ActivityMainBinding
import com.alperen.unitercih.db.DepartmentDatabase
import com.alperen.unitercih.ui.detail.DetailActivity
import com.alperen.unitercih.utils.Constants

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )

        with(binding) {
            val dbRef = DepartmentDatabase.getInstance(root.context)

            // Fill db for first time
            fillDb(dbRef)

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
                if (!etName.text.isNullOrEmpty()) {
                    if (btnMale.isChecked)
                        startSurvey(etName.text, btnMale)
                    else
                        startSurvey(etName.text, btnFemale)
                } else {
                    nameLayout.error = "Lütfen isminizi giriniz"
                    nameLayout.isErrorEnabled = true
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.btnDetailPage -> {
                val intent = Intent(this, DetailActivity::class.java)
                startActivity(intent)
            }
        }
        return true
    }

    private fun startSurvey(name: Editable?, radioButton: RadioButton) {

    }

    private fun fillDb(dbRef: DepartmentDatabase?) {
        val departments = dbRef?.departmentDAO()?.getAllDepartments()
        if (departments.isNullOrEmpty()) {
            Constants.CONSTANT_DEPARTMENTS.forEach {
                dbRef?.departmentDAO()?.insertDepartment(it)
            }

        }
    }
}