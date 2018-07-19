package dev.shreyansh.peco.Util

import android.content.Context
import android.content.SharedPreferences

class CustomSharedPrefs(context: Context) {

    companion object {
        val PREF: String = "PeCoWM"
        val PREF_NIGHT_MODE: String = "NIGHT_MODE"
        val PREF_THEME: String = "THEME_NUMBER"
    }

    private val prefs: SharedPreferences = context.getSharedPreferences(PREF, 0)

    /* Night Mode SharedPref */
    var themeNightMode: Boolean
        get() = prefs.getBoolean(PREF_NIGHT_MODE, false)
        set(value) = prefs.edit().putBoolean(PREF_NIGHT_MODE, value).apply()

    /* Accent Color SharedPref */
    var accentColor: Int
        get() = prefs.getInt(PREF_THEME, -1)
        set(value) = prefs.edit().putInt(PREF_THEME, value).apply()
}