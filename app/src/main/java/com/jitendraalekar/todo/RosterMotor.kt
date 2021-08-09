package com.jitendraalekar.todo

import androidx.lifecycle.ViewModel

class RosterMotor(val repo: ToDoRepository) : ViewModel() {
    val items = repo.items

    fun save(model: ToDoModel) {
        repo.save(model)
    }
}