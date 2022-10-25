package uz.gita.music_player_io.utils.extensions

fun Long.longToMin(): String {
    val minutes = (this / 1000) / 60
    val seconds = (this / 1000) % 60
    return if (minutes < 10 && seconds < 10) "0$minutes:0$seconds mins"
    else if (minutes < 10) "0$minutes:$seconds mins"
    else if (seconds < 10) "$minutes:0$seconds mins"
    else "$minutes:$seconds mins"
}