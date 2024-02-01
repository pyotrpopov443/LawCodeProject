package pyotr.popov443.lawcodeproject

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val expandCollapseCheckbox: CheckBox = findViewById(R.id.expandCollapseCheckbox)
        val expandCollapseText: TextView = findViewById(R.id.expandCollapseText)

        val doCollapseArticles = getSharedPreferences("settings", Context.MODE_PRIVATE).getBoolean("doCollapseArticles", true)
        if (doCollapseArticles)
        {
            expandCollapseCheckbox.isChecked = true
            expandCollapseText.text = resources.getString(R.string.settings_expand_collapse_on)
        }
        else
        {
            expandCollapseCheckbox.isChecked = false
            expandCollapseText.text = resources.getString(R.string.settings_expand_collapse_off)
        }

        expandCollapseCheckbox.setOnClickListener {
            if (expandCollapseCheckbox.isChecked)
            {
                expandCollapseText.text = resources.getString(R.string.settings_expand_collapse_on)
            }
            else
            {
                expandCollapseText.text = resources.getString(R.string.settings_expand_collapse_off)
            }
            this.saveCollapseArticlesSettings(expandCollapseCheckbox.isChecked)
        }

        expandCollapseText.setOnClickListener {
            if (expandCollapseCheckbox.isChecked)
            {
                expandCollapseCheckbox.isChecked = false
                expandCollapseText.text = resources.getString(R.string.settings_expand_collapse_off)
            }
            else
            {
                expandCollapseCheckbox.isChecked = true
                expandCollapseText.text = resources.getString(R.string.settings_expand_collapse_on)
            }
            this.saveCollapseArticlesSettings(expandCollapseCheckbox.isChecked)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = resources.getString(R.string.action_settings)
    }

    private fun saveCollapseArticlesSettings(doCollapseArticles: Boolean) {
        val sharedPref = getSharedPreferences("settings", Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putBoolean("doCollapseArticles", doCollapseArticles)
            apply()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }

}