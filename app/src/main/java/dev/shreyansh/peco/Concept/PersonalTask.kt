package dev.shreyansh.peco.Concept

import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "personal_task")
data class PersonalTask(

    val taskName: String,

    val timeDeadline:String,

    val dateDeadline:String,

    @Nullable
    val isSubTaskOf: Long?

) {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null

    var completed: Boolean? = false
}