package com.jitendraalekar.todo.ui.edit

import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.jitendraalekar.todo.R
import com.jitendraalekar.todo.databinding.TodoEditBinding
import com.jitendraalekar.todo.repo.ToDoModel
import com.jitendraalekar.todo.ui.SingleModelMotor
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class EditFragment : Fragment() {

    private lateinit var tododEditBinding: TodoEditBinding
    private val editArgs: EditFragmentArgs by navArgs()
    private val singleModelMotor: SingleModelMotor by viewModel { parametersOf(editArgs.modelId) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        TodoEditBinding.inflate(inflater, container, false).apply { tododEditBinding = this }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        singleModelMotor.find()?.let { model ->
            tododEditBinding.apply {
                isCompleted.isChecked = model.isCompleted
                desc.setText(model.description)
                note.setText(model.notes)
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_save, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (R.id.save) {
            item.itemId -> {
                save()
                return true
            }
        }
        when (R.id.delete) {
            item.itemId -> {
                deleteModel()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun navigateToList() {
        hideKeyboard()
        findNavController().popBackStack(R.id.rosterListFragment, false)

    }

    private fun deleteModel() {
        singleModelMotor.delete()
        navigateToList()

    }

    private fun save() {
        val model = singleModelMotor.getModel()?.let {
            it.copy(
                isCompleted = tododEditBinding.isCompleted.isChecked,
                description = tododEditBinding.desc.text.toString(),
                notes = tododEditBinding.note.text.toString()
            )
        } ?: ToDoModel(
            isCompleted = tododEditBinding.isCompleted.isChecked,
            description = tododEditBinding.desc.text.toString(),
            notes = tododEditBinding.note.text.toString()
        )

        singleModelMotor.save(model)

        navToDisplay()
    }

    private fun navToDisplay() {
        hideKeyboard()
        findNavController().popBackStack()
    }

    private fun hideKeyboard() {
        view?.let {
            val imm = context?.getSystemService<InputMethodManager>()
            imm?.hideSoftInputFromWindow(
                it.windowToken, InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }
}