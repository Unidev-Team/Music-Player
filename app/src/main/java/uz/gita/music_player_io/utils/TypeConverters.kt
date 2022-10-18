package uz.gita.music_player_io.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uz.gita.music_player_io.data.model.MusicData
import java.util.*

class MusicListTypeConverter {

    private val gson = Gson()

    @TypeConverter
    fun stringToList(data: String?): List<MusicData> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<MusicData>>() {

        }.type

        return gson.fromJson<List<MusicData>>(data, listType)
    }

    @TypeConverter
    fun listToString(someObjects: List<MusicData>): String {
        return gson.toJson(someObjects)
    }
}