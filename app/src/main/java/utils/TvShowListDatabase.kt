package utils

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import model.TVShow

@Database(entities = [TVShow::class], version = 1, exportSchema = false)
abstract class TvShowListDatabase : RoomDatabase() {
    abstract fun tvShowListDao(): TvShowListDAO

    companion object{

//        Singleton
        @Volatile
        private var INSTANCE : TvShowListDatabase? = null

        fun getDatabase(context: Context):TvShowListDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TvShowListDatabase::class.java,
                    "tvshowlist_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}