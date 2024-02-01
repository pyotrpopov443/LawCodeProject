package pyotr.popov443.lawcodeproject.ui.videos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pyotr.popov443.lawcodeproject.R


class VideosFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_videos, container, false)
        val recyclerView: RecyclerView = root.findViewById(R.id.videoListsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val videoLists = mutableListOf<VideoList>()
        for (i in 1..7) {
            val videoListNameId = resources.getIdentifier("videoList${i}_name", "string", context?.packageName)
            val videoListVideosId = resources.getIdentifier("videoList${i}_videos", "string", context?.packageName)
            val videoListName = resources.getString(videoListNameId)
            val videos = resources.getString(videoListVideosId).split(' ').filter { s -> s != "" }.map {id ->
                if (id.contains("yandex:"))
                {
                    val yandexId = id.split('#')[0].drop(7)
                    val yandexThumbnail = id.split('#')[1]
                    Video(yandexId).createForYandex(yandexThumbnail)
                }
                else
                {
                    Video(id).createForYoutube()
                }
            }
            if (videos.isNotEmpty()) {
                videoLists.add(VideoList(videoListName, videos))
            }
        }

        val videoListAdapter = VideoListsAdapter(videoLists)
        recyclerView.adapter = videoListAdapter

        return root
    }
}