package com.jitendraalekar.todo

import androidx.lifecycle.ViewModel

class RosterMotor(val repo: ToDoRepository) : ViewModel() {
    fun getItems() = repo.items


    fun save(model: ToDoModel) {
        repo.save(model)
    }
}