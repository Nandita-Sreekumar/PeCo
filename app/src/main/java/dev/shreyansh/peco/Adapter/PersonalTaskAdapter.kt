package dev.shreyansh.peco.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.shreyansh.peco.Concept.PersonalTask
import dev.shreyansh.peco.R


class PersonalTaskAdapter(context: Context) : RecyclerView.Adapter<PersonalTaskAdapter.PersonalTaskHolder>() {
    private val mInflater: LayoutInflater
    private var personalTaskList: List<PersonalTask>? = null // Cached copy of words
    private val ctx: Context = context

    inner class PersonalTaskHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val personalTaskTitle: TextView = itemView.findViewById(R.id.pt_reminder_title)
        val personalTaskDeadline: TextView = itemView.findViewById(R.id.pt_reminder_deadline)
    }

    init {
        mInflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonalTaskHolder {
        val itemView = mInflater.inflate(R.layout.personal_task_row, parent, false)
        return PersonalTaskHolder(itemView)
    }

    override fun onBindViewHolder(holder: PersonalTaskHolder, position: Int) {
        if (personalTaskList != null) {
            val currentTaskTitle = personalTaskList!![position]
            val currentTaskDeadline = ctx.resources.getString(R.string.pt_reminder_date, personalTaskList!![position].timeDeadline, personalTaskList!![position].dateDeadline)
            holder.personalTaskTitle.text = currentTaskTitle.taskName
            holder.personalTaskDeadline.text = currentTaskDeadline
        } else {
            // Covers the case of data not being ready yet.
            holder.personalTaskTitle.text = "No Word"
            holder.personalTaskDeadline.text = "..."
        }
    }

    internal fun setWords(words: List<PersonalTask>) {
        personalTaskList = words
        notifyDataSetChanged()
    }

    // getItemCount() is called many times, and when it is first called,
    // personalTaskList has not been updated (means initially, it's null, and we can't return null).
    override fun getItemCount(): Int {
        return if (personalTaskList != null)
            personalTaskList!!.size
        else
            0
    }
}