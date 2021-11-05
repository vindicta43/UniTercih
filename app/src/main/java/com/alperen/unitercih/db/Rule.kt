package com.alperen.unitercih.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Alperen on 5.11.2021.
 */
@Entity(tableName = "rules")
data class Rule(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Int,

    @ColumnInfo(name = "rule")
    var rule : String,

    @ColumnInfo(name = "elements")
    var elements : List<Int>
)