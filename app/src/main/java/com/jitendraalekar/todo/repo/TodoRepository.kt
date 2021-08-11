package com.jitendraalekar.todo

import com.jitendraalekar.todo.repo.ToDoModel

class ToDoRepository {
    var items = emptyList<ToDoModel>()

    fun save(model: ToDoModel) {
        items = if (items.any { it.id == model.id }) {
//            items.filter { it.id == model.id}.map { model }
            items.map { if (it.id == model.id) model else it }
        } else {
            items + model
        }
    }

    fun delete(modelId: String?) {
        items = items.filter { it.id != modelId }
    }
}