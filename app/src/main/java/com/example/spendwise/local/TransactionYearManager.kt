package com.example.spendwise.local

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class TransactionYearManager(app: Application) {
    private val sharedPreferences: SharedPreferences =
        app.getSharedPreferences("transaction_year_prefs", Context.MODE_PRIVATE)

    private val yearsKey = "years_key"
    private val isInitializedKey = "years_initialized"

    fun getYears(): List<String> {
        if (!isInitialized()) {
            initializeYears()
        }
        return sharedPreferences.getStringSet(yearsKey, emptySet())?.toList()
            ?.sortedDescending() ?: emptyList()
    }

    fun addYear(year: String) {
        val years = sharedPreferences.getStringSet(yearsKey, emptySet())?.toMutableSet()
            ?: mutableSetOf()
        if (years.add(year)) {
            sharedPreferences.edit().putStringSet(yearsKey, years).apply()
        }
    }

    private fun isInitialized(): Boolean {
        return sharedPreferences.getBoolean(isInitializedKey, false)
    }

    private fun initializeYears() {
        sharedPreferences.edit()
            .putStringSet(yearsKey, emptySet())
            .putBoolean(isInitializedKey, true)
            .apply()
    }
}
