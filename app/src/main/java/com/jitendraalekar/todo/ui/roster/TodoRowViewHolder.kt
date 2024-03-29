package com.jitendraalekar.todo.ui.roster

import androidx.recyclerview.widget.RecyclerView
import com.jitendraalekar.todo.databinding.TodoRowBinding
import com.jitendraalekar.todo.repo.ToDoModel

class TodoRowViewHolder(val binding: TodoRowBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        item: ToDoModel,
        onCheck: (model: ToDoModel) -> Unit,
        onRowClick: (model: ToDoModel) -> Unit
    ) {
        binding.apply {
            root.setOnClickListener { onRowClick(item) }
            isCompleted.isChecked = item.isCompleted
            desc.text = item.description
            isCompleted.setOnCheckedChangeListener { _, _ -> onCheck(item) }
        }
    }

}

