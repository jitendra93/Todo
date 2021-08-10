package com.jitendraalekar.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.jitendraalekar.todo.databinding.TodoEditBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class EditFragment : Fragment() {

    private lateinit var tododEditBinding: TodoEditBinding
    private val editArgs: EditFragmentArgs by navArgs()
    private val singleModelMotor: SingleModelMotor by viewModel { parametersOf(editArgs.modelId) }

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

}