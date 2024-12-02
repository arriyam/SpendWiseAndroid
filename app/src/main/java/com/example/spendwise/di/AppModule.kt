package com.example.spendwise.di

import android.content.Context
import androidx.room.Room
import com.example.spendwise.local.AppDatabase
import com.example.spendwise.local.CategoryManager
import com.example.spendwise.local.TransactionDao
import com.example.spendwise.local.TransactionYearManager
import com.example.spendwise.repository.TransactionRepository
import com.example.spendwise.repository.TransactionRepositoryImpl
import com.example.spendwise.viewmodel.SummaryViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.viewModel
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
    single { CategoryManager(androidApplication()) }
    single { TransactionYearManager(get()) }
    single<TransactionRepository> { TransactionRepositoryImpl(get()) }
    viewModel { SummaryViewModel() }
}
