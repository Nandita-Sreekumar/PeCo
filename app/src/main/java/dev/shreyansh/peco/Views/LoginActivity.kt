package dev.shreyansh.peco.Views

import android.graphics.Typeface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.shreyansh.peco.R
import dev.shreyansh.peco.Util.CustomSharedPrefs
import dev.shreyansh.peco.Util.ThemeSupport
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val APP_NAME_STRING = "PeCo\nWork Manager"
    /* Late Initialize these variables */
    private lateinit var themeSupport: ThemeSupport
    private lateinit var prefs: CustomSharedPrefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /* Initialize Custom Prefs */
        runOnUiThread {
            prefs = CustomSharedPrefs(this)
            themeSupport = ThemeSupport(this, null)

            /* Set accent color and Night mode as per pref*/
            themeSupport.setTheme()
            themeSupport.nightMode(delegate)
        }

        setContentView(R.layout.activity_login)

        loginText()
    }

    private fun loginText() {
        val lobster = Typeface.createFromAsset(assets, "fonts/lobster.otf")
        login_app_name.typeface = lobster
        login_app_name.text = APP_NAME_STRING
    }
}
