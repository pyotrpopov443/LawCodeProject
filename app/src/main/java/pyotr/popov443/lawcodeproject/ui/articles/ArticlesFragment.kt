package pyotr.popov443.lawcodeproject.ui.articles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import pyotr.popov443.lawcodeproject.R


class ArticlesFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_articles, container, false)
        val listView: ListView = root.findViewById(R.id.list_articles)

        val articles = mutableListOf<Article>()
        for (i in 1..13) {
            val articleNameId = resources.getIdentifier("article${i}_name", "string", context?.packageName)
            val articleTextId = resources.getIdentifier("article${i}_text", "string", context?.packageName)
            val articleName = resources.getString(articleNameId)
            val articleText = resources.getString(articleTextId)
            articles.add(Article(articleName, articleText))
        }

        val arrayAdapter = ArticleListAdapter(requireContext(), articles)
        listView.adapter = arrayAdapter

        listView.setOnItemClickListener { parent, view, position, id ->
            val arguments = ArticleActivity.Arguments(articles[position])
            arguments.startActivity(requireContext())
        }

        return root
    }
}