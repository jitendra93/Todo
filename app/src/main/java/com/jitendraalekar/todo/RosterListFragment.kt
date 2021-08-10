package com.jitendraalekar.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jitendraalekar.todo.databinding.TodoRosterBinding
import org.koin.android.viewmodel.ext.android.viewModel

class RosterListFragment : Fragment() {

    val rosterMotor: RosterMotor by viewModel()
    private lateinit var binding: TodoRosterBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = TodoRosterBinding.inflate(inflater, container, false).apply { binding = this }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val todoAdapter = TodoAdapter(
            layoutInflater,
            // lambda as last argument to function
            onCheck = { rosterMotor.save(it.copy(isCompleted = !it.isCompleted)) },
            onRowClick = ::displayModel
        )

        binding.items.apply {
            adapter = todoAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
            )
        }

        todoAdapter.submitList(rosterMotor.getItems())
        binding.empty.visibility = View.GONE
    }

    fun displayModel(model: ToDoModel) {
        findNavController().navigate(RosterListFragmentDirections.displayModel(model.id))
    }
}