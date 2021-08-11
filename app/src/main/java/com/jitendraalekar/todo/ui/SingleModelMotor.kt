package com.jitendraalekar.todo.ui

import androidx.lifecycle.ViewModel
import com.jitendraalekar.todo.ToDoRepository
import com.jitendraalekar.todo.repo.ToDoModel

class SingleModelMotor(
    private val repo: ToDoRepository,
    private val modelId: String?,
) : ViewModel() {

    fun find() = repo.items.find { it.id == modelId }

    fun getModel() = find()

    fun save(model: ToDoModel) {
        repo.save(model)
    }

    fun delete() = repo.delete(modelId)
}