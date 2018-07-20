/*
 *
 * MIT License
 *
 * Copyright (c) 2018 Shreyansh Lodha
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package dev.shreyansh.peco.Util

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import androidx.fragment.app.DialogFragment
import dev.shreyansh.peco.Adapter.CustomThemeAdapter
import dev.shreyansh.peco.Concept.Theme
import dev.shreyansh.peco.R




class CustomDialogFrag : DialogFragment(), AdapterView.OnItemClickListener{

    lateinit var listView: ListView
    lateinit var ctx: Context

    companion object {
        val themes: ArrayList<Theme> = ThemeSupport.getAllThemes()

        // We are not using the title here.
        fun newInstance(title: Int): CustomDialogFrag {
            val custom = CustomDialogFrag()
            val bundle = Bundle()
            bundle.putInt("title", title)
            custom.arguments = bundle
            return custom
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        val customAdapter = CustomThemeAdapter(ctx, themes)
        val builder = AlertDialog.Builder(activity)

        // Get the layout inflater
        val inflater = activity!!.layoutInflater

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        val view = inflater.inflate(R.layout.custom_dialog_view, null)

        // Set view for the dialog
        builder.setView(view)

        // set Title of the dialog
        builder.setTitle("Choose theme")

        listView = view.findViewById(R.id.list) as ListView
        listView.adapter = customAdapter
        listView.onItemClickListener = this

        return builder.create()
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        ctx = activity.applicationContext
    }


    override fun onItemClick(adapter: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        val prefs = CustomSharedPrefs(ctx)
        prefs.accentColor = pos-1
        this.dismiss()
        sendResult()
    }

    // This won't be used but we need it to recreate the activity
    private fun sendResult() {
        val intent = Intent()
        intent.putExtra("CHANGE", true)
        targetFragment!!.onActivityResult(
                targetRequestCode, 1, intent)
    }

}