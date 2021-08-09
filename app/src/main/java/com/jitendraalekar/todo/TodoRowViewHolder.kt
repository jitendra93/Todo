package com.jitendraalekar.todo

import androidx.recyclerview.widget.RecyclerView
import com.jitendraalekar.todo.databinding.TodoRowBinding

class TodoRowViewHolder(val binding: TodoRowBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ToDoModel, onCheck: (model: ToDoModel) -> Unit) {
        binding.apply {
            isCompleted.isChecked = item.isCompleted
            desc.text = item.description
            isCompleted.setOnCheckedChangeListener { _, _ -> onCheck(item) }
        }
    }

}

