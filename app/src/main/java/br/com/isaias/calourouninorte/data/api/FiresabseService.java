package br.com.isaias.calourouninorte.data.api;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

import br.com.isaias.calourouninorte.utils.Result;

public class FiresabseService {
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private Result<FirebaseUser> result;

    private Result<FirebaseUser> login(String email, String password){
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                result = new Result.Success(firebaseAuth.getCurrentUser());
            }else{
                new Result.Error(new Exception());
            }
        });
        return result;
    }
}
