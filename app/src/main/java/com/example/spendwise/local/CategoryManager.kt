package com.example.spendwise.local

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.spendwise.provider.DefaultCategoryProvider

class CategoryManager(app: Application) {
    private val sharedPreferences: SharedPreferences =
        app.getSharedPreferences("categories_prefs", Context.MODE_PRIVATE)

    private val categoriesKey = "categories_key"
    private val isInitializedKey = "categories_initialized"

    fun getCategories(): List<String> {
        if (!isInitialized()) {
            initializeDefaultCategories()
        }
        return sharedPreferences.getStringSet(categoriesKey, DefaultCategoryProvider.getCategories())?.toList()
            ?.sorted() ?: DefaultCategoryProvider.getCategories().toList()
    }

    fun addCategory(category: String) {
        val categories = sharedPreferences.getStringSet(categoriesKey, DefaultCategoryProvider.getCategories())?.toMutableSet()
            ?: mutableSetOf()
        categories.add(category)
        sharedPreferences.edit().putStringSet(categoriesKey, categories).apply()
    }

    fun removeCategory(category: String) {
        val categories = sharedPreferences.getStringSet(categoriesKey, DefaultCategoryProvider.getCategories())?.toMutableSet()
            ?: mutableSetOf()
        categories.remove(category)
        sharedPreferences.edit().putStringSet(categoriesKey, categories).apply()
    }


    private fun isInitialized(): Boolean {
        return sharedPreferences.getBoolean(isInitializedKey, false)
    }

    private fun initializeDefaultCategories() {
        sharedPreferences.edit()
            .putStringSet(categoriesKey, DefaultCategoryProvider.getCategories())
            .putBoolean(isInitializedKey, true)
            .apply()
    }
}
