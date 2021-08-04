package dev.davidvarela.social.data.localDataSource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.davidvarela.social.data.entities.DataComment
import dev.davidvarela.social.data.entities.DataPostPreview
import dev.davidvarela.social.data.entities.DataUser
import dev.davidvarela.social.data.localDataSource.daos.CommentDao
import dev.davidvarela.social.data.localDataSource.daos.PostPreviewDao
import dev.davidvarela.social.data.localDataSource.daos.UserDao

@Database(
    entities = [DataPostPreview::class, DataUser::class, DataComment::class],
    version = 1
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun postPreviewDao(): PostPreviewDao
    abstract fun commentDao(): CommentDao

    companion object {
        @Volatile
        private var instance: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase = instance ?: synchronized(this) {
            instance ?: Room.databaseBuilder(context, AppDataBase::class.java, "db").build()
                .also { instance = it }
        }
    }
}
