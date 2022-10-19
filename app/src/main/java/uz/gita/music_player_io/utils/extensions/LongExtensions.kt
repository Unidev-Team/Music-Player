package uz.gita.music_player_io.utils.extensions

fun Long.longToMin(): String {
    val minutes = (this / 1000) / 60
    val seconds = (this / 1000) % 60
    return "$minutes:$seconds mins"
}