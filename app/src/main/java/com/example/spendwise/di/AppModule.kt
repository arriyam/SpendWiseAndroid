package com.example.spendwise.di

import android.content.Context
import androidx.room.Room
import com.example.spendwise.local.AppDatabase
import com.example.spendwise.local.CategoryManager
import com.example.spendwise.local.TransactionDao
import com.example.spendwise.local.TransactionYearManager
import org.koin.dsl.module

val appModule = module {
    single { (context: Context) ->
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    single<TransactionDao> { get<AppDatabase>().transactionDao() }
    single { (context: Context) -> CategoryManager(context) }
    single { (context: Context) -> TransactionYearManager(context) }
}
