package com.alperen.unitercih.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

/**
 * Created by Alperen on 4.11.2021.
 */
@Dao
interface DepartmentDAO {
    @Insert
    fun insertDepartment(vararg departments: Department)

    @Delete
    fun deleteDepartment(department: Department)

    @Query("SELECT * FROM departments")
    fun getAllDepartments() : List<Department>

    @Query("SELECT * FROM departments WHERE :field = :isTrueOrFalse")
    fun getFromDepartmentsWithField(field: String, isTrueOrFalse: Boolean) : List<Department>


//    @Insert
//    fun insertRule(vararg rules: Rule)
//
//    @Delete
//    fun deleteRule(rule: Rule)
//
//    @Query("SELECT * FROM rules")
//    fun getAllRules() : List<Rule>
//
//    @Query("SELECT * FROM rules WHERE rule = :rule")
//    fun getFromRulesWithField(rule : String)
}