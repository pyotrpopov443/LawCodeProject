package pyotr.popov443.lawcodeproject.ui.videos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pyotr.popov443.lawcodeproject.R


class VideoListsAdapter(
    private val videoLists: List<VideoList>
) : RecyclerView.Adapter<VideoListsAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(videoList: VideoList) {
            val recyclerView: RecyclerView = itemView.findViewById(R.id.videosRecyclerView)
            recyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)

            val userVerticalAdapter = VideosAdapter(videoList.videos)
            recyclerView.adapter = userVerticalAdapter
//            recyclerView.scrollToPosition(Int.MAX_VALUE/2)

            val textVideoListName: TextView = itemView.findViewById(R.id.textVideoListName)
            textVideoListName.text = videoList.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_video_list, parent, false)
        )

    override fun getItemCount(): Int = videoLists.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(videoLists[position])
    }
}
