package dev.shreyansh.peco

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import dev.shreyansh.peco.Concept.PersonalTask
import dev.shreyansh.peco.Dao.PersonalTaskDao


class PeCoRepository internal constructor(application: Application) {

    private val personalTaskDao: PersonalTaskDao
    internal val allTasks: LiveData<List<PersonalTask>>

    init {
        val db = PeCoRoomDatabase.getDatabase(application)
        personalTaskDao = db!!.personalTaskDao()
        allTasks = personalTaskDao.getAllTasks()
    }


    fun insert(personaltask: PersonalTask) {
        insertAsyncTask(personalTaskDao).execute(personaltask)
    }

    private class insertAsyncTask internal constructor(private val mAsyncTaskDao: PersonalTaskDao) : AsyncTask<PersonalTask, Void, Void>() {

        override fun doInBackground(vararg params: PersonalTask): Void? {
            mAsyncTaskDao.insert(params[0])
            Log.i("-----------", params[0].taskName)
            return null
        }
    }
}