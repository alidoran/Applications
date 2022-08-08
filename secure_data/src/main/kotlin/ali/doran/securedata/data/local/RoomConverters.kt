package ali.doran.securedata.data.local


import androidx.room.TypeConverter
import java.util.*

class RoomConverters {
    //for date and time conversions
    @TypeConverter
    fun calendarToDateStamp(calendar: Calendar): Long = calendar.timeInMillis

    @TypeConverter
    fun dateStampToCalendar(value: Long): Calendar =
        Calendar.getInstance().apply { timeInMillis = value }
}