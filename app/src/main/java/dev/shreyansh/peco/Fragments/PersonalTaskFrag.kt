package dev.shreyansh.peco.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dev.shreyansh.peco.Adapter.PersonalTaskAdapter
import dev.shreyansh.peco.Concept.PersonalTask
import dev.shreyansh.peco.R
import dev.shreyansh.peco.Views.AddPersonalTaskActivity

class PersonalTaskFrag : Fragment() {

    companion object {
        fun newInstance() = PersonalTaskFrag()
    }

    private lateinit var viewModel: PersonalTaskFragViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var PTA: PersonalTaskAdapter

            override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.personal_task_fragment, container, false)
        val fab: FloatingActionButton = view.findViewById(R.id.personal_task_add)
        fab.setOnClickListener { startActivityForResult(Intent(this.activity, AddPersonalTaskActivity::class.java), 0) }

        recyclerView = view.findViewById<RecyclerView>(R.id.personal_task_recycler_view)
        PTA = PersonalTaskAdapter(this.context!!)
        recyclerView.adapter = PTA
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PersonalTaskFragViewModel::class.java)
        viewModel.allTask.observe(this, object : Observer<List<PersonalTask>> {
            override fun onChanged(words: List<PersonalTask>) {
                // Update the cached copy of the words in the adapter.
                PTA.setWords(words)
        }})
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        PTA.notifyDataSetChanged()
    }

}
