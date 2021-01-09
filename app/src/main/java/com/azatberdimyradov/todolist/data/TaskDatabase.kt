package com.azatberdimyradov.todolist.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.azatberdimyradov.todolist.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase: RoomDatabase() {

    abstract fun taskDao(): TaskDao

    class Callback @Inject constructor(
        private val database: Provider<TaskDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ): RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao = database.get().taskDao()

            applicationScope.launch {
                dao.insert(Task("Azat"))
                dao.insert(Task("Berdi"))
                dao.insert(Task("Rahym",important = true))
                dao.insert(Task("Atacan",completed = true))
                dao.insert(Task("Kubilay"))
                dao.insert(Task("Arslan",completed = true))
                dao.insert(Task("Ibrahim"))
            }

        }
    }

}