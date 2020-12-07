package br.com.isaias.calourouninorte.ui.singup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import br.com.isaias.calourouninorte.databinding.FragmentSignUpBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.android.viewmodel.ext.android.viewModel

class SingUpFragment : Fragment() {

    private val singUpViewModel: SingUpViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentSignUpBinding.inflate(inflater, container, false).let {
        it.lifecycleOwner = viewLifecycleOwner
        it.viewModel = singUpViewModel
        it.executePendingBindings()
        it.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback {
            findNavController().popBackStack()
        }
        singUpViewModel.singUp().observe(viewLifecycleOwner, Observer {
            if (it != null){
                singUpViewModel.createUser()
                Snackbar.make(view,"O usuário foi criado com Sucesso",Snackbar.LENGTH_SHORT ).show()
                findNavController().navigate(SingUpFragmentDirections.actionSingUpFragmentToStudentListFragment())
            }else{
                Snackbar.make(view,"O usuário não pode ser criado",Snackbar.LENGTH_SHORT ).show()
            }
        })
    }
}