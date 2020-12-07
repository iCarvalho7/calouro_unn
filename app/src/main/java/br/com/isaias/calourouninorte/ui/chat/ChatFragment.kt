package br.com.isaias.calourouninorte.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.isaias.calourouninorte.data.model.Message
import br.com.isaias.calourouninorte.databinding.FragmentChatBinding
import kotlinx.android.synthetic.main.fragment_chat.*
import org.koin.android.viewmodel.ext.android.viewModel

class ChatFragment : Fragment(){

    private val viewModel : ChatViewModel by viewModel()

    private val args : ChatFragmentArgs by navArgs()
    private lateinit var adapter  : ChatListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentChatBinding.inflate(inflater, container, false).let {
        it.lifecycleOwner = viewLifecycleOwner
        it.user = args.user
        it.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ChatListAdapter(
            mutableListOf(
                Message(content = "Ola, tudo bem? Você poderia me ajudar?", isRecived = false),
                Message(content = "Sou calouro do curso de Ciencia da Computação e Gostaria de Tirar umas dúvidas", isRecived = false),
                Message(content = "Na disciplina de lógica de Programação.", isRecived = false),
                Message(content = "Opa, posso sim.", isRecived = true),
                Message(content = "Quais as suas dúvidas em relação a essa disciplina?", isRecived = true),
                Message(content = "Sobre os conceitos de vetores e matrizes", isRecived = false),
                Message(content = "E também estou com dificuldade pra entender ponteiros", isRecived = false),
                Message(content = "Ah sim compreendo, no começo tive bastante dificulade também.", isRecived = true),
                Message(content = "Vou te passar umas vídeo-aula que me ajudaram muito no começo.", isRecived = true)
            )
        )

        recycler_chat.adapter = adapter
        setUpListeners()
    }

    private fun setUpListeners() {
        activity?.onBackPressedDispatcher?.addCallback { findNavController().popBackStack() }
        toolbar_chat.setNavigationOnClickListener { findNavController().popBackStack() }
        frag_chat_message_layout.setEndIconOnClickListener {
            val messageToSent = frag_chat_message_input.text.toString()
            adapter.addMessage(Message(content = messageToSent, isRecived = false))
            frag_chat_message_input.setText("")
        }
    }
}