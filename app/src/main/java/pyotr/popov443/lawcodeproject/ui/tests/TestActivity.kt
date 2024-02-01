package pyotr.popov443.lawcodeproject.ui.tests

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pyotr.popov443.lawcodeproject.R
import pyotr.popov443.lawcodeproject.ui.articles.Article
import pyotr.popov443.lawcodeproject.ui.articles.ArticleActivity

class TestActivity : AppCompatActivity() {

    class Arguments(val test: Test?) {

        companion object {
            private const val TEST = "test"

            fun createFromIntent(intent: Intent): Arguments {
                return Arguments(intent.getParcelableExtra(TEST))
            }
        }

        fun startActivity(context: Context) {
            val intent = Intent(context, TestActivity::class.java)
            intent.putExtra(TEST, test)
            context.startActivity(intent)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        val test = Arguments.createFromIntent(intent).test!!

        val recyclerView: RecyclerView = findViewById(R.id.questionsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        recyclerView.isNestedScrollingEnabled = false

        val questionsAdapter = QuestionsAdapter(test.questions)
        recyclerView.adapter = questionsAdapter

        val resultText: TextView = findViewById(R.id.resultText)

        resultText.setOnClickListener {
            val bullingArticleName = resources.getString(R.string.article4_name)
            val bullingArticleText = resources.getString(R.string.article4_text)
            val arguments = ArticleActivity.Arguments(Article(bullingArticleName, bullingArticleText))
            arguments.startActivity(applicationContext)
        }

        val resultButton: Button = findViewById(R.id.resultButton)
        resultButton.setOnClickListener {
            if (test.questions.any { q -> q.answer == null })
            {
                Toast.makeText(applicationContext, resources.getString(R.string.you_did_not_answered_every_question), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (test.isParent)
            {
                resultText.text = test.result
                resultText.visibility = View.VISIBLE
                resultButton.text = "Завершить"
                resultButton.setOnClickListener {
                    finish()
                }
                return@setOnClickListener
            }

            val totalCount = test.questions.size
            val correctCount = test.questions.filter { q -> q.answer!!.isCorrect }.size
            val correctAnswers = resources.getQuantityString(R.plurals.correct_answers, correctCount)
            val testResultText = "У тебя $correctCount $correctAnswers из $totalCount\n${test.result}"
            resultText.text = testResultText
            resultText.visibility = View.VISIBLE
            resultButton.text = "Завершить"
            resultButton.setOnClickListener {
                finish()
            }

            questionsAdapter.notifyDataSetChanged()
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = test.name
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