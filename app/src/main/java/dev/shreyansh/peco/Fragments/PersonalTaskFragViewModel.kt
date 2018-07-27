package dev.shreyansh.peco.Fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import dev.shreyansh.peco.Concept.PersonalTask
import dev.shreyansh.peco.PeCoRepository


class PersonalTaskFragViewModel(application: Application) : AndroidViewModel(application) {

    private val mRepository: PeCoRepository

    internal val allTask: LiveData<List<PersonalTask>>

    init {
        mRepository = PeCoRepository(application)
        allTask = mRepository.allTasks
    }

    fun insert(task: PersonalTask) {
        mRepository.insert(task)
    }
}