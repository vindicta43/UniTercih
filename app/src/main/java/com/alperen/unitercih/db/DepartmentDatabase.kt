package com.alperen.unitercih.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Created by Alperen on 4.11.2021.
 */
@Database(entities = [Department::class], version = 1)
abstract class DepartmentDatabase : RoomDatabase() {
    abstract fun departmentDAO(): DepartmentDAO

    companion object {
        private var dbInstance: DepartmentDatabase? = null

        fun getInstance(context: Context): DepartmentDatabase? {
            if (dbInstance == null) {
                dbInstance = Room.databaseBuilder(
                    context,
                    DepartmentDatabase::class.java,
                    "database.db"
                ).allowMainThreadQueries()
                    .build()
            }
            return dbInstance
        }
    }
}