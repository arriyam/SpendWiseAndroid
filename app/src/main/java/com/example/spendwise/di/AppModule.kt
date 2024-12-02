package com.example.spendwise.di

import androidx.room.Room
import com.example.spendwise.local.AppDatabase
import com.example.spendwise.local.CategoryManager
import com.example.spendwise.local.TransactionDao
import com.example.spendwise.local.TransactionYearManager
import com.example.spendwise.repository.TransactionRepository
import com.example.spendwise.repository.TransactionRepositoryImpl
import com.example.spendwise.viewmodel.AddTransactionViewModel
import com.example.spendwise.viewmodel.CategoryViewModel
import com.example.spendwise.viewmodel.TransactionListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    single<TransactionDao> { get<AppDatabase>().transactionDao() }
    single { CategoryManager(androidApplication()) }
    single { TransactionYearManager(androidApplication()) }
    single<TransactionRepository> { TransactionRepositoryImpl(get()) }
    viewModel { CategoryViewModel() }
    viewModel { AddTransactionViewModel() }
    viewModel { TransactionListViewModel() }
}
