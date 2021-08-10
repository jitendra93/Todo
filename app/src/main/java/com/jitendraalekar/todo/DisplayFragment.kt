package com.jitendraalekar.todo

import android.os.Bundle
import android.text.format.DateUtils
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.jitendraalekar.todo.databinding.TodoDisplayBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DisplayFragment : Fragment() {

    private lateinit var binding: TodoDisplayBinding

    private val args: DisplayFragmentArgs by navArgs()

    private val motor: SingleModelMotor by viewModel { parametersOf(args.modelId) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.edit, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.edit -> {
                editModel()
                return true
            }
            R.id.delete -> {
                findNavController().popBackStack()
                deleteModel()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteModel() {
        motor.delete()
    }

    fun editModel() {
        findNavController().navigate(DisplayFragmentDirections.editModel(modelId = args.modelId))
    }
}