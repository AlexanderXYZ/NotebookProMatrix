package com.buslaev.notebookpro.di

import android.content.Context
import androidx.room.Room
import com.buslaev.notebookpro.data.locale.Migrations
import com.buslaev.notebookpro.data.locale.Migrations.MIGRATION_3_4
import com.buslaev.notebookpro.data.locale.NotebookDao
import com.buslaev.notebookpro.data.locale.NotebookDatabase
import com.buslaev.notebookpro.data.locale.repository.NotebookRepositoryImpl
import com.buslaev.notebookpro.domain.repository.NotebookRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NotebookDatabase =
        Room.databaseBuilder(
            context,
            NotebookDatabase::class.java,
            "notebook_database"
        ).addMigrations(Migrations.MIGRATION_1_2, Migrations.MIGRATION_2_3,MIGRATION_3_4)
            .createFromAsset("database/category_table.db")
            .build()

    @Provides
    @Singleton
    fun provideDao(database: NotebookDatabase): NotebookDao = database.getDao()

    @Provides
    @Singleton
    fun provideRepository(dao: NotebookDao): NotebookRepository = NotebookRepositoryImpl(dao)

}