package com.example.spendwise.local

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class CategoryManager(app: Application) {
    private val sharedPreferences: SharedPreferences =
        app.getSharedPreferences("categories_prefs", Context.MODE_PRIVATE)

    private val categoriesKey = "categories_key"
    private val isInitializedKey = "categories_initialized"

    fun getCategories(): List<String> {
        if (!isInitialized()) {
            initializeDefaultCategories()
        }
        return sharedPreferences.getStringSet(categoriesKey, getDefaultCategories())?.toList()
            ?.sorted() ?: getDefaultCategories().toList()
    }

    fun addCategory(category: String) {
        val categories = sharedPreferences.getStringSet(categoriesKey, getDefaultCategories())?.toMutableSet()
            ?: mutableSetOf()
        categories.add(category)
        sharedPreferences.edit().putStringSet(categoriesKey, categories).apply()
    }

    fun removeCategory(category: String) {
        val categories = sharedPreferences.getStringSet(categoriesKey, getDefaultCategories())?.toMutableSet()
            ?: mutableSetOf()
        categories.remove(category)
        sharedPreferences.edit().putStringSet(categoriesKey, categories).apply()
    }

    private fun getDefaultCategories(): Set<String> {
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
            "Other"
        )
    }

    private fun isInitialized(): Boolean {
        return sharedPreferences.getBoolean(isInitializedKey, false)
    }

    private fun initializeDefaultCategories() {
        sharedPreferences.edit()
            .putStringSet(categoriesKey, getDefaultCategories())
            .putBoolean(isInitializedKey, true)
            .apply()
    }
}
