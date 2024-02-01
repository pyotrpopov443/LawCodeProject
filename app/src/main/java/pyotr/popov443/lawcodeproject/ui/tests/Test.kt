package pyotr.popov443.lawcodeproject.ui.tests

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Test(val name: String, val questions: List<Question>, val result: String, val isParent: Boolean) : Parcelable
