package dev.shreyansh.peco.Views

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.button.MaterialButton
import dev.shreyansh.peco.Concept.PersonalTask
import dev.shreyansh.peco.Fragments.PersonalTaskFragViewModel
import dev.shreyansh.peco.R



class AddPersonalTaskFragment : Fragment() {

    companion object {
        fun newInstance() = AddPersonalTaskFragment()
    }

    private lateinit var title: EditText
    private lateinit var time: EditText
    private lateinit var date: EditText
    private lateinit var isallDay: CheckBox
    private lateinit var insertButton: MaterialButton
    private lateinit var personalTaskViewModel: PersonalTaskFragViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.add_personal_task_fragment, container, false)
        title = view.findViewById(R.id.add_task_title)
        time = view.findViewById(R.id.add_task_time)
        date = view.findViewById(R.id.add_task_date)
        isallDay = view.findViewById(R.id.add_task_all_day_check)
        insertButton = view.findViewById(R.id.add_task_insert)

        isallDay.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) time.visibility = View.GONE
            else time.visibility = View.VISIBLE
        }

        insertButton.setOnClickListener( { insert() } )

        val imm = this.activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(date.windowToken, 0)



        return  view
    }

    private fun insert() {
        val personalTask: PersonalTask = PersonalTask(title.text.toString(), date.text.toString(), time.text.toString(), null)
        personalTaskViewModel = ViewModelProviders.of(this).get(PersonalTaskFragViewModel::class.java)
        personalTaskViewModel.insert(personalTask)
        this.activity?.finish()
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}
