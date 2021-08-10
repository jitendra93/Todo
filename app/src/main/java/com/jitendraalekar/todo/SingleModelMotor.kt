package com.jitendraalekar.todo

import androidx.lifecycle.ViewModel

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