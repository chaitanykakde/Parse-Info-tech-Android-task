package com.chaitany.chaitanystask.utils

import android.content.Context
import android.content.SharedPreferences
import com.chaitany.chaitanystask.data.User
import com.google.gson.Gson

class PreferenceManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val gson = Gson()
    
    companion object {
        private const val PREFS_NAME = "EduLearnPrefs"
        private const val KEY_USER = "user"
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
        private const val KEY_WATCHLIST = "watchlist"
    }
    
    fun saveUser(user: User) {
        val userJson = gson.toJson(user)
        prefs.edit().putString(KEY_USER, userJson).apply()
    }
    
    fun getUser(): User? {
        val userJson = prefs.getString(KEY_USER, null)
        return if (userJson != null) {
            gson.fromJson(userJson, User::class.java)
        } else {
            null
        }
    }
    
    fun setLoggedIn(isLoggedIn: Boolean) {
        prefs.edit().putBoolean(KEY_IS_LOGGED_IN, isLoggedIn).apply()
    }
    
    fun isLoggedIn(): Boolean {
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false)
    }
    
    fun clearUserData() {
        prefs.edit().clear().apply()
    }
    
    fun addToWatchlist(course: com.chaitany.chaitanystask.data.Course) {
        val watchlist = getWatchlist().toMutableList()
        if (!watchlist.any { it.id == course.id }) {
            watchlist.add(course)
            val watchlistJson = gson.toJson(watchlist)
            prefs.edit().putString(KEY_WATCHLIST, watchlistJson).apply()
        }
    }
    
    fun removeFromWatchlist(courseId: String) {
        val watchlist = getWatchlist().toMutableList()
        watchlist.removeAll { it.id == courseId }
        val watchlistJson = gson.toJson(watchlist)
        prefs.edit().putString(KEY_WATCHLIST, watchlistJson).apply()
    }
    
    fun getWatchlist(): List<com.chaitany.chaitanystask.data.Course> {
        val watchlistJson = prefs.getString(KEY_WATCHLIST, null)
        return if (watchlistJson != null) {
            try {
                val type = object : com.google.gson.reflect.TypeToken<List<com.chaitany.chaitanystask.data.Course>>() {}.type
                gson.fromJson(watchlistJson, type) ?: emptyList()
            } catch (e: Exception) {
                emptyList()
            }
        } else {
            emptyList()
        }
    }
    
    fun isInWatchlist(courseId: String): Boolean {
        return getWatchlist().any { it.id == courseId }
    }
}
