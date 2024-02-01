package pyotr.popov443.lawcodeproject.ui.articles

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import pyotr.popov443.lawcodeproject.R

class ArticleActivity : AppCompatActivity() {

    class Arguments(val article: Article?) {

        companion object {
            private const val TEXT = "article"

            fun createFromIntent(intent: Intent): Arguments {
                return Arguments(intent.getParcelableExtra(TEXT))
            }
        }

        fun startActivity(context: Context) {
            val intent = Intent(context, ArticleActivity::class.java)
            intent.putExtra(TEXT, article)
            intent.flags = FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        val doCollapseArticles = getSharedPreferences("settings", Context.MODE_PRIVATE).getBoolean("doCollapseArticles", true)

        val article = Arguments.createFromIntent(intent).article!!
        val articleView: WebView = findViewById(R.id.html_article)
        articleView.settings.javaScriptEnabled = doCollapseArticles
        val articleJsCss = if (doCollapseArticles) resources.getString(R.string.article_js_css) else ""
        articleView.loadData(articleJsCss + article.text, "text/html; charset=utf-8", "UTF-8")

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = article.name
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