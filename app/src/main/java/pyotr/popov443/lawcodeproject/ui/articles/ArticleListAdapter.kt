package pyotr.popov443.lawcodeproject.ui.articles

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import pyotr.popov443.lawcodeproject.R

class ArticleListAdapter(private val context: Context,
                         private val articles: List<Article>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int = articles.size

    override fun getItem(position: Int): Article = articles[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView = inflater.inflate(R.layout.item_article, parent, false)

        val article = getItem(position)
        val articleTitle: TextView = rowView.findViewById(R.id.textArticleTitle)
        articleTitle.text = article.name

        return rowView
    }

}