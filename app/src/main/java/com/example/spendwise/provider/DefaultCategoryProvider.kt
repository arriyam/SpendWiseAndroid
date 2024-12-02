package com.example.spendwise.provider

object DefaultCategoryProvider {
    fun getCategories(): Set<String> {
        return setOf(
            "Rent",
            "Utilities",
            "Groceries",
            "Restaurants",
            "Outside Food",
            "Entertainment",
            "Subscriptions",
            "Travelling Costs",
            "Education",
            "Hygiene",
            "Other",
        )
    }
}