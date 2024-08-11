package edu.divyagyan.deliveryserviceapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import edu.divyagyan.deliveryserviceapp.model.HelperClass;

public class SignupViewModel extends ViewModel {
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference reference = database.getReference("users");

    private final MutableLiveData<String> signupStatus = new MutableLiveData<>();

    public LiveData<String> getSignupStatus() {
        return signupStatus;
    }

    public void signUpUser(String name, String email, String username, String password, String address, String phone) {
        HelperClass helperClass = new HelperClass(name, email, username, password, phone, address);
        reference.child(username).setValue(helperClass)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        signupStatus.setValue("Sign Up Successful");
                    } else {
                        signupStatus.setValue("Sign Up Failed: " + task.getException().getMessage());
                    }
                });
    }
}
