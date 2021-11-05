package com.alperen.unitercih.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alperen.unitercih.R
import com.alperen.unitercih.db.Department
import com.alperen.unitercih.db.DepartmentDatabase

/**
 * Created by Alperen on 5.11.2021.
 */
class DepartmentRecyclerViewAdapter(private var list: MutableList<Department>) :
    RecyclerView.Adapter<DepartmentRecyclerViewAdapter.MyViewHolder>() {
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDepartmentName: TextView = itemView.findViewById(R.id.tvDepartmentName)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        val btnDeleteDepartment: ImageButton = itemView.findViewById(R.id.btnDeleteDepartment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_item_department, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvDepartmentName.text = list[position].departmentName
        holder.tvDescription.text = list[position].toString()
        holder.btnDeleteDepartment.setOnClickListener {
            val dbRef = DepartmentDatabase.getInstance(holder.itemView.context)
            dbRef?.departmentDAO()?.deleteDepartment(list[position])

            // Delete department
            list.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, list.size)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}