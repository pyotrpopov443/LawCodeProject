package pyotr.popov443.lawcodeproject.ui.videos

class Video(var id: String) {
    var url: String = ""
    var thumbnail: String = ""

    fun createForYoutube(): Video
    {
        url = "https://youtu.be/$id"
        thumbnail = "https://img.youtube.com/vi/$id/mqdefault.jpg"
        return this
    }

    fun createForYandex(thumbnailUrl: String): Video
    {
        url = "https://yandex.ru/video/preview/$id"
        thumbnail = thumbnailUrl
        return this
    }
}
