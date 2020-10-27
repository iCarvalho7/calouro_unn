package br.com.isaias.calourouninorte.ui.students_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.isaias.calourouninorte.databinding.FragmentStudentsListBinding

class StudentListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentStudentsListBinding.inflate(inflater, container, false).root
    }
}