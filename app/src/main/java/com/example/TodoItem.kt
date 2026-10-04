package com.example

/**
 * Represents a single to-do item.
 * Beginner-friendly data class with an id, title text, and completion status.
 */
data class TodoItem(
    val id: Long = System.currentTimeMillis(),
    val text: String,
    val isCompleted: Boolean = false
)
