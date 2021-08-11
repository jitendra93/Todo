package com.jitendraalekar.todo.ui.roster

import androidx.lifecycle.ViewModel
import com.jitendraalekar.todo.ToDoRepository
import com.jitendraalekar.todo.repo.ToDoModel

class RosterMotor(val repo: ToDoRepository) : ViewModel() {
    fun getItems() = repo.items


    fun save(model: ToDoModel) {
        repo.save(model)
    }
}