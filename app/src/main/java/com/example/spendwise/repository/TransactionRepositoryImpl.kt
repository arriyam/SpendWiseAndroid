package com.example.spendwise.repository

import com.example.spendwise.local.TransactionDao
import com.example.spendwise.local.TransactionEntity

class TransactionRepositoryImpl(
    private val transactionDao: TransactionDao
):TransactionRepository {

    override suspend fun insertTransaction(transaction: TransactionEntity): Long {
        return transactionDao.insertTransaction(transaction)
    }

    override suspend fun updateTransaction(transaction: TransactionEntity) {
        transactionDao.updateTransaction(transaction)
    }

    override suspend fun deleteTransaction(transaction: TransactionEntity) {
        transactionDao.deleteTransaction(transaction)
    }

    override suspend fun getAllTransactions(): List<TransactionEntity> {
        return transactionDao.getAllTransactions()
    }

    override suspend fun getTransactionsBetweenDates(startDate: Long, endDate: Long): List<TransactionEntity> {
        return transactionDao.getTransactionsBetweenDates(startDate, endDate)
    }
    override suspend fun getTransactionsBasedOnFilter(
        year: String?,
        month: String?,
        category: String?
    ): List<TransactionEntity> {
        return transactionDao.getTransactionsBasedOnFilter(year, month, category)
    }
}
