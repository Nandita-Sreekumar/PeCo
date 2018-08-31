package dev.shreyansh.peco.Views

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager

import android.widget.CheckBox
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TimePicker
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.button.MaterialButton
import dev.shreyansh.peco.Concept.PersonalTask
import dev.shreyansh.peco.Fragments.PersonalTaskFragViewModel
import dev.shreyansh.peco.R
import dev.shreyansh.peco.Receivers.AlarmReceiver
import java.text.SimpleDateFormat
import java.util.*
class AddPersonalTaskFragment : Fragment() {

    companion object {
        fun newInstance() = AddPersonalTaskFragment()
    }

    private lateinit var title: EditText
    private lateinit var time: EditText
    private lateinit var am: AlarmManager
    private lateinit var date: EditText
    private lateinit var isallDay: CheckBox
    private lateinit var insertButton: MaterialButton
    private lateinit var pi:PendingIntent
    private lateinit var personalTaskViewModel: PersonalTaskFragViewModel
    var s_hour:Int=0
    var s_min:Int=0
    var s_day:Int=0
    var s_month:Int=0
    var s_year:Int=0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.add_personal_task_fragment, container, false)
        title = view.findViewById(R.id.add_task_title)
        time = view.findViewById(R.id.add_task_time)
        date = view.findViewById(R.id.add_task_date)
        isallDay = view.findViewById(R.id.add_task_all_day_check)
        insertButton = view.findViewById(R.id.add_task_insert)
        am = activity?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        var formate = SimpleDateFormat("dd/MM/yyyy",Locale.US)
        var timeFormat = SimpleDateFormat("hh:mm", Locale.US)
        isallDay.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) time.visibility = View.GONE
            else time.visibility = View.VISIBLE
        }
        val now=Calendar.getInstance()
        var myIntent: Intent = Intent(context, AlarmReceiver::class.java)


//        val imm = this.activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        imm.hideSoftInputFromWindow(date.windowToken, 0)

        val DatePicker = object: DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
                val selectedDate = Calendar.getInstance()
                selectedDate.set(Calendar.YEAR, year)
                selectedDate.set(Calendar.MONTH, monthOfYear)
                selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                s_year=year
                s_month=monthOfYear
                s_day=dayOfMonth
                val date1 = formate.format(selectedDate.time)
                date.setText(date1.toString())
            }
        }
        date.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View) {
//                println("Bro going inside function")
//                activity?.hideKeyboard(view)

                DatePickerDialog(context,DatePicker,now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),now.get(Calendar.DAY_OF_MONTH)).show()


            }
        })
        val selectedTime = Calendar.getInstance()
        val TimePicker = TimePickerDialog.OnTimeSetListener {
             view, hourOfDay, minute->
                selectedTime.set(Calendar.HOUR_OF_DAY,hourOfDay)
                selectedTime.set(Calendar.MINUTE,minute)
                s_hour=hourOfDay
                s_min=minute
                val time1 = timeFormat.format(selectedTime.time)
                time.setText(time1.toString()) 
            }

        time.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View){

            TimePickerDialog(context,TimePicker,now.get(Calendar.HOUR_OF_DAY),
                    now.get(Calendar.MINUTE),false).show()


                }

        })

        insertButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                insert()
                pi=PendingIntent.getBroadcast(context,0,myIntent,PendingIntent.FLAG_UPDATE_CURRENT)
                am.setExact(AlarmManager.RTC_WAKEUP,now.timeInMillis,pi)
            }
        })

//        fun Context.hideKeyboard(view: View) {
//            val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
//            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
//        }



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
