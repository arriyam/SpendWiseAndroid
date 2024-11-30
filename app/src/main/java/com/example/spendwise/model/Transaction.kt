package com.example.spendwise.model

data class Transaction(
    val id: Long,
    val title: String,
    val amountCents: Long,
    val date: Long,
    val category: String,
    val notes: String?
) {
    fun amountInDollars(): Double = amountCents / 100.0
}