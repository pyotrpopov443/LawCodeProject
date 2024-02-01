package pyotr.popov443.lawcodeproject.ui.tests

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Question(val question: String, val answers: List<Answer>) : Parcelable
{
    var answer: Answer? = null
}
