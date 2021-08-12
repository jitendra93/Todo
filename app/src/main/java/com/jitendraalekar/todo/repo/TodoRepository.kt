package com.jitendraalekar.todo

import com.jitendraalekar.todo.repo.ToDoEntity
import com.jitendraalekar.todo.repo.ToDoModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class ToDoRepository(
    private val todoStore: ToDoEntity.Store,
    private val appScope: CoroutineScope
) {

    fun items(): Flow<List<ToDoModel>> =
        todoStore.all().map { all ->
            all.map {
                it.toModel()
            }
        }

    fun find(id: String?): Flow<ToDoModel?> = todoStore.find(id).map { it.toModel() }


    suspend fun save(model: ToDoModel) {
        withContext(appScope.coroutineContext) {
            todoStore.save(ToDoEntity(model))
        }
    }

    suspend fun delete(model: ToDoModel) {
        withContext(appScope.coroutineContext) {
            todoStore.delete(ToDoEntity(model))
        }
    }
}