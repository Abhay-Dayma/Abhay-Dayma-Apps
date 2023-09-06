package com.example.notesabhay_dayma

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NoteDao {

   @Insert(onConflict = OnConflictStrategy.IGNORE)
   suspend fun insert(note:Note)

    @Delete
    suspend fun delete(note:Note)

    @Query("Select * from notes_table order by Note_Id DESC")
    fun getAllNotes(): LiveData<List<Note>>
}