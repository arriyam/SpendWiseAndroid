package com.example.spendwise.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update

@Dao
interface TransactionDao {
    @Insert
    suspend fun insertTransaction(transaction: TransactionEntity): Long

    @Update
    suspend fun updateTransaction(transaction: TransactionEntity)

    @Delete
    suspend fun deleteTransaction(transaction: TransactionEntity)

    @Query("SELECT * FROM transactions")
    suspend fun getAllTransactions(): List<TransactionEntity>

    @Query("SELECT * FROM transactions WHERE date BETWEEN :startDate AND :endDate")
    suspend fun getTransactionsBetweenDates(startDate: Long, endDate: Long): List<TransactionEntity>

    @Query("""
        SELECT * FROM transactions
        WHERE (:year IS NULL OR strftime('%Y', date / 1000, 'unixepoch') = :year)
          AND (:month IS NULL OR strftime('%m', date / 1000, 'unixepoch') = :month)
          AND (:category IS NULL OR category = :category)
        ORDER BY date DESC
    """)
    suspend fun getTransactionsBasedOnFilter(
        year: String? = null,
        month: String? = null,
        category: String? = null
    ): List<TransactionEntity>
}

