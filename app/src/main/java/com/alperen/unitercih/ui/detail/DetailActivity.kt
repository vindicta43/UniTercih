package com.alperen.unitercih.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.alperen.unitercih.R
import com.alperen.unitercih.databinding.ActivityDetailBinding
import com.alperen.unitercih.db.Department
import com.alperen.unitercih.db.DepartmentDatabase
import com.alperen.unitercih.ui.add.AddDepartmentActivity
import com.alperen.unitercih.utils.Constants

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

            rootLayout.setOnRefreshListener {
                val departmentRefresh = dbRef.departmentDAO().getAllDepartments()

                recyclerDepartments.layoutManager = LinearLayoutManager(root.context)
                recyclerDepartments.adapter = DepartmentRecyclerViewAdapter(departmentRefresh)

                rootLayout.isRefreshing = false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.detail_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btnAddDepartment -> {
                val intent = Intent(this, AddDepartmentActivity::class.java)
                startActivity(intent)
            }
        }
        return true
    }
}