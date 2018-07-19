package dev.shreyansh.peco.Adapter

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.core.content.res.ResourcesCompat
import dev.shreyansh.peco.Concept.Theme
import dev.shreyansh.peco.R
import dev.shreyansh.peco.Util.CustomSharedPrefs


class CustomThemeAdapter(private val mContext: Context, @LayoutRes list: ArrayList<Theme>) : ArrayAdapter<Theme>(mContext, 0, list){
    private var themeList = ArrayList<Theme>()
    private var prefs: CustomSharedPrefs
    private val textColor: Int

    companion object {
        const val THEME = "Theme "
    }

    init {
        themeList = list
        prefs = CustomSharedPrefs(mContext)
        if (prefs.themeNightMode)
            textColor = context.resources.getColor(R.color.textSecondary)
        else
            textColor = context.resources.getColor(R.color.textPrimary)

    }



    override fun getCount(): Int {
        return themeList.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var listItem: View? = convertView

        if (listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.custom_theme_pref, parent, false)
        }

        val currentTheme = themeList[position]

        val themeTitle = listItem?.findViewById(R.id.themeTitle) as TextView
        themeTitle.setTextColor(textColor)
        themeTitle.text = THEME + (position + 1)

        val colorBase = listItem.findViewById(R.id.colorBase) as Button
        var gradientDrawable: GradientDrawable = colorBase.background as GradientDrawable
        gradientDrawable.setColor(ResourcesCompat.getColor(mContext.resources, currentTheme.primaryColor, null))

        val colorAccent = listItem.findViewById(R.id.colorAccent) as Button
        gradientDrawable = colorAccent.background as GradientDrawable
        gradientDrawable.setColor(ResourcesCompat.getColor(mContext.resources, currentTheme.accentColor, null))

        return listItem
    }
}