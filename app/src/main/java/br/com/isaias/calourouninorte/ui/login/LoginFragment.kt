package br.com.isaias.calourouninorte.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import br.com.isaias.calourouninorte.R
import br.com.isaias.calourouninorte.databinding.FragmentLoginBinding
import br.com.isaias.calourouninorte.ui.singup.SingUpFragmentDirections
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentLoginBinding.inflate(inflater, container, false).let {
        it.lifecycleOwner = viewLifecycleOwner
        it.viewModel = viewModel
        it.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val text = view.findViewById<TextView>(R.id.login_frag_text_not_registred)
        val button_login = view.findViewById<Button>(R.id.login_frag_btn_join)

        activity?.onBackPressedDispatcher?.addCallback {
            findNavController().popBackStack()
        }
        text.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSingUpFragment())
        }

        viewModel.login().observe(viewLifecycleOwner, Observer {
            if (it != null){
                viewModel.userInteraction.postValue(true)
                Snackbar.make(view,"Login feito com Sucesso",Snackbar.LENGTH_SHORT ).show()
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToStudentListFragment())
            }else{
                Snackbar.make(view,"Erro ao fazer Login",Snackbar.LENGTH_SHORT ).show()
            }
        })

        viewModel.userInteraction.observe(viewLifecycleOwner, Observer {
        })

    }
}