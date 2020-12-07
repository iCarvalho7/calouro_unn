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
import br.com.isaias.calourouninorte.data.model.User
import br.com.isaias.calourouninorte.databinding.FragmentLoginBinding
import br.com.isaias.calourouninorte.ui.singup.SingUpFragmentDirections
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_login.*
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
        setListeners()
        setObservers(view)
    }

    private fun setObservers(view: View) {
        viewModel.login(false).observe(viewLifecycleOwner, Observer {
            viewModel.userInteraction.postValue(true)
            if (it != null) {
                navigateToStudentList()
            } else {
//                Snackbar.make(view, "Erro ao fazer Login", Snackbar.LENGTH_SHORT).show()
            }
        })

        viewModel.userLogged.observe(viewLifecycleOwner, Observer {
//            Snackbar.make(view, "User", Snackbar.LENGTH_SHORT).show()
            navigateToStudentList()
        })

        viewModel.userFirebaseLogged.observe(viewLifecycleOwner, Observer {
            if (it.data != null){
//                Snackbar.make(view, "Firebase User", Snackbar.LENGTH_SHORT).show()
                navigateToStudentList()
            }
        })
    }

    private fun setListeners() {
        activity?.onBackPressedDispatcher?.addCallback {
            findNavController().popBackStack()
        }
        login_frag_text_not_registred.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSingUpFragment())
        }
    }

    private fun navigateToStudentList(){
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToStudentListFragment())
    }
}