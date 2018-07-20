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