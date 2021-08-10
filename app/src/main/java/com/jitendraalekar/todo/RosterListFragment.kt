package com.jitendraalekar.todo

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jitendraalekar.todo.databinding.TodoRosterBinding
import org.koin.android.viewmodel.ext.android.viewModel

class RosterListFragment : Fragment() {

    val rosterMotor: RosterMotor by viewModel()
    private lateinit var binding: TodoRosterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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
        binding.empty.visibility = if (todoAdapter.itemCount > 0) View.GONE else View.VISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.actions_roster, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (R.id.add) {
            item.itemId -> {
                createModel()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun displayModel(model: ToDoModel) {
        findNavController().navigate(RosterListFragmentDirections.displayModel(model.id))
    }

    fun createModel() {
        findNavController().navigate(RosterListFragmentDirections.createModel())
    }
}