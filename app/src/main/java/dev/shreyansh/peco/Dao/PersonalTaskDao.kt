package dev.shreyansh.peco.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import dev.shreyansh.peco.Concept.PersonalTask

@Dao
interface PersonalTaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: PersonalTask)

    @Query("DELETE FROM personal_task")
    fun deleteAll()

    @Query("SELECT * FROM PERSONAL_TASK ORDER BY id")
    fun getAllTasks(): LiveData<List<PersonalTask>>

    @Update
    fun updateTask(task: PersonalTask)
}