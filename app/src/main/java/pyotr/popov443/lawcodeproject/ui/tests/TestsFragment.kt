package pyotr.popov443.lawcodeproject.ui.tests

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import pyotr.popov443.lawcodeproject.R

class TestsFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_tests, container, false)
        val listView: ListView = root.findViewById(R.id.list_tests)
        val arrayAdapter: ArrayAdapter<*>

        val tests = mutableListOf<Test>()
        for (t in 1..9) {
            try {
                val testNameId = resources.getIdentifier("test${t}_name", "string", context?.packageName)
                val testName = resources.getString(testNameId)

                val questions = mutableListOf<Question>()
                for (q in 1..100) {
                    try {
                        val questionId = resources.getIdentifier("test${t}_q${q}", "string", context?.packageName)
                        val question = resources.getString(questionId)

                        val answers = mutableListOf<Answer>()
                        for (a in 1..10) {
                            try {
                                val answerId = resources.getIdentifier("test${t}_q${q}_a${a}", "string", context?.packageName)
                                val answer = resources.getString(answerId).drop(1)
                                val answerCorrect = resources.getString(answerId).first() == 't'
                                answers.add(Answer(answer, answerCorrect))
                            } catch (e: Exception) {
                                break
                            }
                        }

                        questions.add(Question(question, answers))
                    } catch (e: Exception) {
                        break
                    }
                }

                var result = resources.getString(R.string.bulling_test_result)
                var isParent = false
                if (t == 2 || t == 3)
                {
                    result = resources.getString(R.string.parent_bulling_test_result)
                    isParent = true
                }

                tests.add(Test(testName, questions, result, isParent))
            } catch (e: Exception) {
                break
            }
        }
        val testNames = tests.map { test -> test.name }

        arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, testNames)
        listView.adapter = arrayAdapter

        listView.setOnItemClickListener { parent, view, position, id ->
            val arguments = TestActivity.Arguments(tests[position])
            arguments.startActivity(requireContext())
        }

        return root
    }
}