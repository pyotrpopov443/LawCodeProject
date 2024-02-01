package pyotr.popov443.lawcodeproject.ui.tests

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import pyotr.popov443.lawcodeproject.R


class QuestionsAdapter(
    private val questions: List<Question>
) : RecyclerView.Adapter<QuestionsAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(question: Question) {
            val questionText: TextView = itemView.findViewById(R.id.questionText)
            questionText.text = question.question
            val radioGroup: RadioGroup = itemView.findViewById(R.id.radio_answers)
            radioGroup.removeAllViews()

            var checkedIndex: Int? = null
            for (answer in question.answers)
            {
                val answerButton = RadioButton(itemView.context)
                answerButton.text = answer.answer

                answerButton.setOnClickListener {
                    question.answer = answer
                }

                if (answer.answer == question.answer?.answer)
                {
                    checkedIndex = radioGroup.childCount
                }

                radioGroup.addView(answerButton)
            }

            if (checkedIndex != null)
            {
                radioGroup.check(radioGroup.getChildAt(checkedIndex).id)
            }

            val questionBackground: ViewGroup = itemView.findViewById(R.id.questionBackground)
            if (question.answer == null)
            {
                questionBackground.background = null
            }

            if (question.answer?.isCorrect == true)
            {
                questionBackground.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.green_200))
            }

            if (question.answer?.isCorrect == false)
            {
                questionBackground.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.red_200))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_question, parent, false)
        )

    override fun getItemCount(): Int = questions.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(questions[position])
    }
}
