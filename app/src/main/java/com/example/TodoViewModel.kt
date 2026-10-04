package com.example

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Simple ViewModel managing the to-do list using LiveData.
 * Follows beginner-friendly Android architecture principles:
 * - Internal state in private MutableLiveData
 * - Public read-only LiveData exposed to the UI
 */
class TodoViewModel : ViewModel() {

    // Internal mutable LiveData holding the list of todos
    private val _todoList = MutableLiveData<List<TodoItem>>(
        listOf(
            TodoItem(id = 1, text = "Buy groceries", isCompleted = false),
            TodoItem(id = 2, text = "Read 10 pages of a book", isCompleted = true),
            TodoItem(id = 3, text = "Walk for 15 minutes", isCompleted = false)
        )
    )

    // Public read-only LiveData observed by Compose UI
    val todoList: LiveData<List<TodoItem>> = _todoList

    /**
     * Adds a new to-do item to the list.
     */
    fun addTodo(text: String) {
        val trimmed = text.trim()
        if (trimmed.isEmpty()) return

        val newItem = TodoItem(
            id = System.currentTimeMillis(),
            text = trimmed,
            isCompleted = false
        )
        val currentList = _todoList.value.orEmpty()
        _todoList.value = currentList + newItem
    }

    /**
     * Toggles the completed status of an item by id.
     */
    fun toggleTodo(id: Long) {
        val currentList = _todoList.value.orEmpty()
        _todoList.value = currentList.map { item ->
            if (item.id == id) {
                item.copy(isCompleted = !item.isCompleted)
            } else {
                item
            }
        }
    }

    /**
     * Deletes an item from the list by id.
     */
    fun deleteTodo(id: Long) {
        val currentList = _todoList.value.orEmpty()
        _todoList.value = currentList.filter { it.id != id }
    }

    /**
     * Removes all completed tasks from the list.
     */
    fun clearCompleted() {
        val currentList = _todoList.value.orEmpty()
        _todoList.value = currentList.filter { !it.isCompleted }
    }
}
