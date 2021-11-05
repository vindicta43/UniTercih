package com.alperen.unitercih

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.alperen.unitercih.databinding.ActivityMainBinding
import com.alperen.unitercih.db.Department
import com.alperen.unitercih.db.DepartmentDatabase
import com.alperen.unitercih.utils.Constants

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

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
            }
        }
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