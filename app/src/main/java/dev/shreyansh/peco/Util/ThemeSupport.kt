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
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.FragmentActivity
import dev.shreyansh.peco.Concept.Theme
import dev.shreyansh.peco.R


class ThemeSupport(context: Context, activity: AppCompatActivity?) {

    private val ctx: Context
    private val act: AppCompatActivity?
    private val customSharedPrefs: CustomSharedPrefs

    init {
        ctx = context
        act = activity
        customSharedPrefs = CustomSharedPrefs(ctx)
    }

    companion object {
        const val THEME_DEFAULT = -1
        const val THEME_RED = 0
        const val THEME_PINK = 1
        const val THEME_PURPLE = 2
        const val THEME_DEEPPURPLE = 3
        const val THEME_INDIGO = 4
        const val THEME_BLUE = 5
        const val THEME_LIGHTBLUE = 6
        const val THEME_CYAN = 7
        const val THEME_TEAL = 8
        const val THEME_GREEN = 9
        const val THEME_LIGHTGREEN = 10
        const val THEME_LIME = 11
        const val THEME_YELLOW = 12
        const val THEME_AMBER = 13
        const val THEME_ORANGE = 14
        const val THEME_DEEPORANGE = 15
        const val THEME_BROWN = 16
        const val THEME_GRAY = 17
        const val THEME_BLUEGRAY = 18

        fun getAllThemes(): ArrayList<Theme> {
            val themeArrayList: ArrayList<Theme> = ArrayList()
            with(themeArrayList) {
                add(Theme(-1, R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent))
                add(Theme(0, R.color.primaryColorRed, R.color.primaryDarkColorRed, R.color.secondaryColorRed))
                add(Theme(1, R.color.primaryColorPink, R.color.primaryDarkColorPink, R.color.secondaryColorPink))
                add(Theme(2, R.color.primaryColorPurple, R.color.primaryDarkColorPurple, R.color.secondaryColorPurple))
                add(Theme(3, R.color.primaryColorDeepPurple, R.color.primaryDarkColorDeepPurple, R.color.secondaryColorDeepPurple))
                add(Theme(4, R.color.primaryColorIndigo, R.color.primaryDarkColorIndigo, R.color.secondaryColorIndigo))
                add(Theme(5, R.color.primaryColorBlue, R.color.primaryDarkColorBlue, R.color.secondaryColorBlue))
                add(Theme(6, R.color.primaryColorLightBlue, R.color.primaryDarkColorLightBlue, R.color.secondaryColorLightBlue))
                add(Theme(7, R.color.primaryColorCyan, R.color.primaryDarkColorCyan, R.color.secondaryColorCyan))
                add(Theme(8, R.color.primaryColorTeal, R.color.primaryDarkColorTeal, R.color.secondaryColorTeal))
                add(Theme(9, R.color.primaryColorGreen, R.color.primaryDarkColorGreen, R.color.secondaryColorGreen))
                add(Theme(10, R.color.primaryColorLightGreen, R.color.primaryDarkColorLightGreen, R.color.secondaryColorLightGreen))
                add(Theme(11, R.color.primaryColorLime, R.color.primaryDarkColorLime, R.color.secondaryColorLime))
                add(Theme(12, R.color.primaryColorYellow, R.color.primaryDarkColorYellow, R.color.secondaryColorYellow))
                add(Theme(13, R.color.primaryColorAmber, R.color.primaryDarkColorAmber, R.color.secondaryColorAmber))
                add(Theme(14, R.color.primaryColorOrange, R.color.primaryDarkColorOrange, R.color.secondaryColorOrange))
                add(Theme(15, R.color.primaryColorDeepOrange, R.color.primaryDarkColorDeepOrange, R.color.secondaryColorDeepOrange))
                add(Theme(16, R.color.primaryColorBrown, R.color.primaryDarkColorBrown, R.color.secondaryColorBrown))
                add(Theme(17, R.color.primaryColorGray, R.color.primaryDarkColorGray, R.color.secondaryColorGray))
                add(Theme(18, R.color.primaryColorBlueGray, R.color.primaryDarkColorBlueGray, R.color.secondaryColorBlueGray))
            }
            return themeArrayList
        }
    }


    fun nightMode(delegate: AppCompatDelegate) {
        when (customSharedPrefs.themeNightMode) {
            true -> {
                delegate.setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            false -> {
                delegate.setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    fun isDarkMode(): Boolean {
        when (customSharedPrefs.themeNightMode) {
            true -> return true
            false -> return false
        }
    }

    fun setTheme() {
        ctx.setTheme(getThemeID(customSharedPrefs.accentColor))
        ctx.theme.applyStyle(getThemeID(customSharedPrefs.accentColor), true)
    }

    fun setFragTheme(fragmentActivity: FragmentActivity) {
        fragmentActivity.setTheme(getThemeID(customSharedPrefs.accentColor))
    }


    /* Basic Theme Setting up Functions Start from Here */
    fun getThemeID(theme: Int): Int {
        var themeId = -1
        when (theme) {
            THEME_DEFAULT -> themeId = R.style.AppTheme
            THEME_RED -> themeId = R.style.AppTheme_RED
            THEME_PINK -> themeId = R.style.AppTheme_PINK
            THEME_PURPLE -> themeId = R.style.AppTheme_PURPLE
            THEME_DEEPPURPLE -> themeId = R.style.AppTheme_DEEPPURPLE
            THEME_INDIGO -> themeId = R.style.AppTheme_INDIGO
            THEME_BLUE -> themeId = R.style.AppTheme_BLUE
            THEME_LIGHTBLUE -> themeId = R.style.AppTheme_LIGHTBLUE
            THEME_CYAN -> themeId = R.style.AppTheme_CYAN
            THEME_TEAL -> themeId = R.style.AppTheme_TEAL
            THEME_GREEN -> themeId = R.style.AppTheme_GREEN
            THEME_LIGHTGREEN -> themeId = R.style.AppTheme_LIGHTGREEN
            THEME_LIME -> themeId = R.style.AppTheme_LIME
            THEME_YELLOW -> themeId = R.style.AppTheme_YELLOW
            THEME_AMBER -> themeId = R.style.AppTheme_AMBER
            THEME_ORANGE -> themeId = R.style.AppTheme_ORANGE
            THEME_DEEPORANGE -> themeId = R.style.AppTheme_DEEPORANGE
            THEME_BROWN -> themeId = R.style.AppTheme_BROWN
            THEME_GRAY -> themeId = R.style.AppTheme_GRAY
            THEME_BLUEGRAY -> themeId = R.style.AppTheme_BLUEGRAY
        }
        return themeId
    }

    fun removeToolbarElevation(choice: Boolean) {
        if (choice)
            act?.supportActionBar?.elevation = 0f
    }

}
