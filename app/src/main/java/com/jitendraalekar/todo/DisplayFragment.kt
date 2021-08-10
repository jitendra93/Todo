package com.jitendraalekar.todo

import android.os.Bundle
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.jitendraalekar.todo.databinding.TodoDisplayBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DisplayFragment : Fragment() {

    private lateinit var binding: TodoDisplayBinding

    private val args: DisplayFragmentArgs by navArgs()

    private val motor: SingleModelMotor by viewModel { parametersOf(args.id) }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return TodoDisplayBinding.inflate(inflater, container, false).apply { binding = this }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        motor.find()?.let { model ->
            binding.apply {
                isCompleted.visibility = if (model.isCompleted) View.VISIBLE else View.GONE
                desc.text = model.description
                createdTime.text = DateUtils.getRelativeDateTimeString(
                    requireContext(),
                    model.createdOn.toEpochMilli(), DateUtils.MINUTE_IN_MILLIS,
                    DateUtils.WEEK_IN_MILLIS, 0
                )
                body.text = model.notes
            }
        }
    }
}