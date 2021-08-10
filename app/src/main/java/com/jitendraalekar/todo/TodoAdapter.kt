package com.jitendraalekar.todo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.jitendraalekar.todo.databinding.TodoRowBinding


class TodoAdapter(
    private val layoutInflater: LayoutInflater,
    val onCheck: (model: ToDoModel) -> Unit,
    val onRowClick: (model: ToDoModel) -> Unit
) : ListAdapter<ToDoModel, TodoRowViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoRowViewHolder {
        return TodoRowViewHolder(TodoRowBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: TodoRowViewHolder, position: Int) {
        holder.bind(getItem(position), onCheck, onRowClick)
    }
}

private object DiffCallback : DiffUtil.ItemCallback<ToDoModel>() {
    override fun areItemsTheSame(oldItem: ToDoModel, newItem: ToDoModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ToDoModel, newItem: ToDoModel): Boolean {
        return (oldItem.isCompleted == newItem.isCompleted) && (oldItem.description == newItem.description)
    }
}