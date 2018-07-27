package dev.shreyansh.peco.Views

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import dev.shreyansh.peco.R
import dev.shreyansh.peco.Util.CustomSharedPrefs
import dev.shreyansh.peco.Util.ThemeSupport

class AddPersonalTaskActivity : AppCompatActivity() {

    /* Late Initialize these variables */
    private lateinit var themeSupport: ThemeSupport
    private lateinit var prefs: CustomSharedPrefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        this.supportActionBar?.hide()
/* Initialize Custom Prefs */
        runOnUiThread {
            prefs = CustomSharedPrefs(this)
            themeSupport = ThemeSupport(this, this)

            /* Set accent color and Night mode as per pref*/
            themeSupport.setTheme()
            themeSupport.nightMode(delegate)
        }
        setContentView(R.layout.add_personal_task_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, AddPersonalTaskFragment.newInstance())
                    .commitNow()
        }
    }

}
