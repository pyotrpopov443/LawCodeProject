package pyotr.popov443.lawcodeproject.ui.tests

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Answer(val answer: String, val isCorrect: Boolean) : Parcelable
