package br.com.isaias.calourouninorte.ui.students_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import br.com.isaias.calourouninorte.R
import br.com.isaias.calourouninorte.data.model.User
import br.com.isaias.calourouninorte.databinding.FragmentStudentsListBinding
import com.google.android.material.internal.NavigationMenuView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.fragment_students_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class StudentListFragment : Fragment(){

    private val viewModel : StudentListViewModel by viewModel()
    private lateinit var adapter : StudentListAdapter
    private lateinit var bind : FragmentStudentsListBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentStudentsListBinding.inflate(inflater, container, false)
        val actionBar = ActionBarDrawerToggle(activity, drawer_layout, students_toolbar,R.string.open, R.string.close)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapter()
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