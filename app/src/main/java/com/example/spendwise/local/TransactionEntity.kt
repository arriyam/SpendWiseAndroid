package com.example.spendwise.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.spendwise.model.Transaction

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val amountCents: Long,
    val date: Long,
    val category: String,
    val notes: String? = null
) {
    fun toTransaction(): Transaction {
        return Transaction(
            id = id,
            title = title,
            amountCents = amountCents,
            date = date,
            category = category,
            notes = notes
        )
    }

    companion object {
        fun fromTransaction(transaction: Transaction): TransactionEntity {
            return TransactionEntity(
                id = transaction.id,
                title = transaction.title,
                amountCents = transaction.amountCents,
                date = transaction.date,
                category = transaction.category,
                notes = transaction.notes
            )
        }
    }
}



