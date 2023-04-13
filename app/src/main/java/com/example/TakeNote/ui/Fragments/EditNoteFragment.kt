package com.example.TakeNote.ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.text.method.ScrollingMovementMethod
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.TakeNote.Model.Notes
import com.example.TakeNote.R
import com.example.TakeNote.databinding.FragmentEditNoteBinding
import com.example.TakeNote.ui.Activities.MainActivity
import com.example.TakeNote.viewModels.NotesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class EditNoteFragment : Fragment() {
    val notesId by navArgs<EditNoteFragmentArgs>()

    lateinit var binding: FragmentEditNoteBinding
    val viewModel: NotesViewModel by activityViewModels()
    private lateinit var priority: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditNoteBinding.inflate(inflater, container, false)
        setFragmentContent(notesId.notesId)
        setHasOptionsMenu(true);
        onMenuItemClick()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.edtNotes.setMovementMethod(ScrollingMovementMethod())

        setFragmentContent(notesId.notesId)
        onWorkFilterClick()
        onLifeFilterClick()
        onFamilyFilterClick()
        onEntertainmentFilterClick()
        onfinishedEditButtonClick()


    }


    private fun onDeleteNoteClick(view: View) {
        viewModel.deleteNote(notesId.notesId)
        val navHostFragment =
            (requireActivity() as? MainActivity)?.supportFragmentManager?.findFragmentById(R.id.fragmentContainerView) as? NavHostFragment

        val navController = navHostFragment?.findNavController()

        navController?.navigate(R.id.action_editNoteFragment_to_homeFragment)

    }


    private fun onfinishedEditButtonClick() {
        binding.btnEditNote.setOnClickListener {
            val title = binding.edtTitle.text.toString()
            val note = binding.edtNotes.text.toString()
            val d = Date()
            val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy", d.time)
            val notes = Notes(
                title = title,
                notes = note,
                priority = priority,
                date = notesDate.toString()
            )


            if ((title.isNotEmpty() || note.isNotEmpty())) {
                viewModel.updateNotes(notes)

            } else {
                viewModel.deleteNote(notesId.notesId)
                Toast.makeText(activity, "Note has been deleted!", Toast.LENGTH_SHORT).show()

            }

            Navigation.findNavController(it).navigate(R.id.action_editNoteFragment_to_homeFragment)

        }
    }

    private fun onFamilyFilterClick() {
        binding.tvFamily.setOnClickListener {
            priority = "3"
            binding.tvFamily.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_yes, 0, 0, 0);
            binding.tvWork.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            binding.tvLife.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            binding.tvEntertainment.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

        }
    }

    private fun onWorkFilterClick() {
        binding.tvWork.setOnClickListener {
            priority = "1"
            binding.tvWork.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_yes, 0, 0, 0);
            binding.tvFamily.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            binding.tvLife.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            binding.tvEntertainment.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

        }
    }

    private fun onEntertainmentFilterClick() {
        binding.tvEntertainment.setOnClickListener {
            priority = "4"
            binding.tvEntertainment.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_yes,
                0,
                0,
                0
            );
            binding.tvFamily.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            binding.tvLife.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            binding.tvWork.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);


        }
    }

    private fun onLifeFilterClick() {
        binding.tvLife.setOnClickListener {
            priority = "2"
            binding.tvLife.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_yes, 0, 0, 0);
            binding.tvFamily.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            binding.tvWork.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            binding.tvEntertainment.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

        }
    }

    private fun setFragmentContent(id: Int) {

        lifecycleScope.launchWhenStarted {

            viewModel.getNotes(id).collect { note ->
                binding.edtTitle.setText(note.title)
                binding.edtNotes.setText(note.notes)

                when (note.priority) {
                    "1" ->
                        binding.tvWork.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_yes,
                            0,
                            0,
                            0
                        )

                    "2" ->
                        binding.tvLife.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_yes,
                            0,
                            0,
                            0
                        )

                    "3" ->
                        binding.tvFamily.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_yes,
                            0,
                            0,
                            0
                        );

                    "4" ->
                        binding.tvEntertainment.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_yes,
                            0,
                            0,
                            0
                        )

                }

                priority = note.priority


            }
        }

    }

    private fun onMenuItemClick() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menu.clear()
                menuInflater.inflate(R.menu.delete_menu, menu)

            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {

                if (menuItem.itemId == R.id.delete_note) {

                    val dialogSheet = BottomSheetDialog(requireContext(), R.style.BottomSheetStyle)
                    dialogSheet.setContentView(R.layout.dialog_delete)
                    val textViewNo = dialogSheet.findViewById<TextView>(R.id.no)
                    val textViewYes = dialogSheet.findViewById<TextView>(R.id.yes)

                    dialogSheet.show()

                    textViewNo?.setOnClickListener {
                        dialogSheet.dismiss()
                    }
                    textViewYes?.setOnClickListener {
                        onDeleteNoteClick(it)
                        dialogSheet.dismiss()

                    }


                    return true

                }

                return false
            }


        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.delete_note) {

            val dialogSheet = BottomSheetDialog(requireContext(), R.style.BottomSheetStyle)
            dialogSheet.setContentView(R.layout.dialog_delete)
            val textViewNo = dialogSheet.findViewById<TextView>(R.id.no)
            val textViewYes = dialogSheet.findViewById<TextView>(R.id.yes)

            dialogSheet.show()

            textViewNo?.setOnClickListener {
                dialogSheet.dismiss()
            }
            textViewYes?.setOnClickListener {
                onDeleteNoteClick(it)
                dialogSheet.dismiss()

            }


            return false

        } else if (item.itemId == android.R.id.home) {

            requireActivity().onBackPressed()
            return false
        }


        return super.onOptionsItemSelected(item)
    }


}