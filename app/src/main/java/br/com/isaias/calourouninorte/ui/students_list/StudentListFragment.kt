package br.com.isaias.calourouninorte.ui.students_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import br.com.isaias.calourouninorte.data.model.User
import br.com.isaias.calourouninorte.databinding.FragmentStudentsListBinding
import kotlinx.android.synthetic.main.fragment_students_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class StudentListFragment : Fragment() {

    private val viewModel : StudentListViewModel by viewModel()
    private lateinit var adapter : StudentListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentStudentsListBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapter()
        activity?.onBackPressedDispatcher?.addCallback {  }
        viewModel.fetchUsersFromFirebase().observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
            students_recylerView.adapter = adapter
        })
    }

    private fun setUpAdapter() {
        adapter  = StudentListAdapter(viewModel, object : StudentListAdapter.OnItemClickListener{
            override fun onclick(item: User) {
                findNavController().navigate(StudentListFragmentDirections.actionStudentListFragmentToChatFragment(item))
            }
        })
    }
}