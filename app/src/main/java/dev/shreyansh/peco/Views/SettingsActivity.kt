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

package dev.shreyansh.peco.Views

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import dev.shreyansh.peco.R.xml.preferences
import dev.shreyansh.peco.Util.CustomDialogFrag
import dev.shreyansh.peco.Util.CustomSharedPrefs
import dev.shreyansh.peco.Util.ThemeSupport


class SettingsActivity : AppCompatActivity() {

    private lateinit var prefs: CustomSharedPrefs
    private lateinit var themeSupport: ThemeSupport
    private lateinit var frag: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
         * Run Theme related stuff of UI thread as they are directly related to UI
         */
        runOnUiThread {
            prefs = CustomSharedPrefs(this)
            themeSupport = ThemeSupport(this, this)

            /* Set accent color and Night mode as per pref */
            themeSupport.nightMode(delegate)
            themeSupport.setTheme()
            themeSupport.removeToolbarElevation(true)

        }
        frag = supportFragmentManager
        this.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (frag.findFragmentById(android.R.id.content) == null)
            frag.beginTransaction().add(android.R.id.content, SettingsFragment()).commit()
    }

    class SettingsFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener,
    Preference.OnPreferenceClickListener {

        private val TAG = SettingsFragment::class.java.simpleName as String

        private var nightMode: SwitchPreference? = null
        private var accentColor: Preference? = null
        private var prefs: SharedPreferences? = null
        private var  frag: FragmentManager? = null


        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            prefs = this.activity?.getSharedPreferences(CustomSharedPrefs.PREF, 0)
            val themeSupport = ThemeSupport(this.activity!!.applicationContext, null)
            themeSupport.setFragTheme(this.activity!!)

            addPreferencesFromResource(preferences)

            /* Hook NightTheme to our SharedPreference */
            prefs = this.activity?.getSharedPreferences(CustomSharedPrefs.PREF, 0)
            accentColor = findPreference(CustomSharedPrefs.PREF_THEME)
            accentColor?.isIconSpaceReserved = false
            accentColor?.onPreferenceClickListener = this
            nightMode = findPreference(CustomSharedPrefs.PREF_NIGHT_MODE) as SwitchPreference?
            nightMode?.isIconSpaceReserved = false

            when (prefs?.getBoolean(CustomSharedPrefs.PREF_NIGHT_MODE, false)) {
                true -> nightMode?.isChecked = true
                false -> nightMode?.isChecked = false
            }

            frag = activity?.supportFragmentManager
        }


        // Override the click of Theme preference with our custom dialog
        override fun onPreferenceClick(preference: Preference?): Boolean {
            if (preference is Preference && preference.key == CustomSharedPrefs.PREF_THEME) {
                val dialog = CustomDialogFrag.newInstance(0)
                dialog.setTargetFragment(this, 0)
                dialog.show(frag, "dialog")
                Log.i(TAG, prefs?.getInt(CustomSharedPrefs.PREF_THEME, -1).toString())

                return true
            }
            return false
        }


        override fun onResume() {
            super.onResume()
            preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
        }

        override fun onPause() {
            super.onPause()
            preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)

        }

        // Change pref and recreate, the onCreate() will take care of theme
        override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
            Log.i(TAG, key)
            if (key == CustomSharedPrefs.PREF_NIGHT_MODE) {
                when (prefs?.getBoolean(key, false)) {
                    true -> {
                        prefs?.edit()?.putBoolean(key, false)?.apply()
                    }
                    false -> {
                        prefs?.edit()?.putBoolean(key, true)?.apply()
                    }
                }
                this.activity?.recreate()
            }
        }

        // This gets called when the dialog gets dismissed
        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            this.activity?.recreate()
        }
    }


}
