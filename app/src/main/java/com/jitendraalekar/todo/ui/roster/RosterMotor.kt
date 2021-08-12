package com.jitendraalekar.todo.ui.roster

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.jitendraalekar.todo.ToDoRepository
import com.jitendraalekar.todo.repo.ToDoModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


data class RosterViewState(
    val items: List<ToDoModel> = listOf()
)

class RosterMotor(val repo: ToDoRepository) : ViewModel() {
    val states = repo.items().map { RosterViewState(it) }.asLiveData()


    fun save(model: ToDoModel) {
        viewModelScope.launch {
            repo.save(model)
        }
    }
}