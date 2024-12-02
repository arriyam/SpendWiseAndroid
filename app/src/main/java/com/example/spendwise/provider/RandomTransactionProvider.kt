package com.example.spendwise.provider

import com.example.spendwise.model.Transaction
import java.util.*
import kotlin.random.Random

object RandomTransactionProvider {
    private val titles = listOf(
        "Grocery Shopping",
        "Monthly Rent",
        "Electricity Bill",
        "Dining Out",
        "Movie Tickets",
        "Gym Membership",
        "Online Subscription",
        "Taxi Ride",
        "Coffee Shop",
        "Book Purchase",
        "Clothing",
        "Gasoline",
        "Car Repair",
        "Insurance Payment",
        "Gift Purchase",
        "Pharmacy",
        "Haircut",
        "Hotel Booking",
        "Airline Ticket",
        "Concert Ticket",
        "Streaming Service",
        "Charity Donation",
        "Water Bill",
        "Internet Bill",
        "Mobile Phone Bill",
        "Pet Supplies",
        "Furniture Purchase",
        "Home Improvement",
        "Tuition Fee",
        "Medical Expense",
        "Vacation Expense",
        "Parking Fee",
        "Garden Supplies",
        "Electronics Purchase",
        "Game Purchase",
        "Membership Fee",
        "Loan Payment",
        "Credit Card Payment",
        "Legal Fee",
        "Consultation Fee",
        "Investment",
        "Stock Purchase",
        "Bond Purchase",
        "Tax Payment",
        "Fine Payment",
        "Repair Service",
        "Laundry Service",
        "Courier Service",
        "Child Care",
        "Miscellaneous"
    )

    fun getTransactions(): List<Transaction> {
        val transactions = mutableListOf<Transaction>()
        val random = Random(System.currentTimeMillis())
        val categories = DefaultCategoryProvider.getCategories().toList()

        repeat(50) {
            val title = titles.random(random)
            val amountCents = random.nextLong(100, 10000)
            val date = randomDate(random)
            val category = categories.random(random)
            val transaction = Transaction(
                id = 0,
                title = title,
                amountCents = amountCents,
                date = date,
                category = category,
                notes = null
            )
            transactions.add(transaction)
        }

        return transactions
    }

    private fun randomDate(random: Random): Long {
        val calendar = Calendar.getInstance()
        val year = random.nextInt(2020, 2023)
        val month = random.nextInt(0, 11)
        val day = random.nextInt(1, 28)
        calendar.set(year, month, day)
        return calendar.timeInMillis
    }
}
