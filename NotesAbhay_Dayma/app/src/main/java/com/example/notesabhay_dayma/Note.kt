package com.example.notesabhay_dayma

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
class Note(@ColumnInfo(name = "Text") val text: String) {
    @ColumnInfo(name = "Note_Id") @PrimaryKey(autoGenerate = true) var id = 0
}