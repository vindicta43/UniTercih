package com.alperen.unitercih.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alperen.unitercih.R
import com.alperen.unitercih.databinding.ActivityDetailBinding
import com.alperen.unitercih.db.DepartmentDatabase

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityDetailBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_detail)

        with(binding) {
            val dbRef = DepartmentDatabase.getInstance(root.context)
            val departmentList = dbRef?.departmentDAO()?.getAllDepartments()

            recyclerDepartments.layoutManager = LinearLayoutManager(root.context)
            recyclerDepartments.adapter = DepartmentRecyclerViewAdapter(departmentList!!)
        }
    }
}