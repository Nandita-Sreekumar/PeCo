package dev.shreyansh.peco

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.shreyansh.peco.Concept.PersonalTask
import dev.shreyansh.peco.Dao.PersonalTaskDao

@Database(entities = [(PersonalTask::class)], version = 1)
abstract class PeCoRoomDatabase : RoomDatabase() {
    abstract fun personalTaskDao(): PersonalTaskDao

    companion object {
        private var INSTANCE: PeCoRoomDatabase? = null
        fun getDatabase(context: Context): PeCoRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(PeCoRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                                PeCoRoomDatabase::class.java, "personal_task_database")
                                .build()

                    }
                }
            }
            return INSTANCE
        }
    }

}