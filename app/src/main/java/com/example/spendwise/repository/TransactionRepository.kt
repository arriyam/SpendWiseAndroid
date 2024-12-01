package com.example.spendwise.repository

import com.example.spendwise.local.TransactionEntity

interface TransactionRepository {
    suspend fun insertTransaction(transaction: TransactionEntity): Long
    suspend fun updateTransaction(transaction: TransactionEntity)
    suspend fun deleteTransaction(transaction: TransactionEntity)
    suspend fun getAllTransactions(): List<TransactionEntity>
    suspend fun getTransactionsBetweenDates(startDate: Long, endDate: Long): List<TransactionEntity>
    suspend fun getTransactionsBasedOnFilter(
        year: String? = null,
        month: String? = null,
        category: String? = null
    ): List<TransactionEntity>
}
