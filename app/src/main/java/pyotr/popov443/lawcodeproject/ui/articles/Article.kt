package pyotr.popov443.lawcodeproject.ui.articles

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article(val name: String, val text: String) : Parcelable