package pyotr.popov443.lawcodeproject.ui.videos

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import pyotr.popov443.lawcodeproject.R
import java.net.URL


class VideosAdapter(
        private val videos: List<Video>
) : RecyclerView.Adapter<VideosAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(video: Video) {
            val imageVideoPreview: ImageView = itemView.findViewById(R.id.image_video_preview)

            doAsync {
                val url = URL(video.thumbnail)
                val image = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                uiThread {
                    imageVideoPreview.setImageBitmap(image)
                }
            }

            val buttonStartVideo: ImageButton = itemView.findViewById(R.id.button_video_open)
            buttonStartVideo.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(video.url))
                itemView.context.startActivity(browserIntent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_video, parent, false)
        )

    override fun getItemCount(): Int = videos.size //Int.MAX_VALUE

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val pos = position //position % videos.size
        holder.bind(videos[pos])
    }
}
