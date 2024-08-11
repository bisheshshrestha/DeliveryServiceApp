package edu.divyagyan.deliveryserviceapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginViewModel extends ViewModel {
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference reference = database.getReference("users");

    private final MutableLiveData<String> loginStatus = new MutableLiveData<>();

    public LiveData<String> getLoginStatus() {
        return loginStatus;
    }

    public void loginUser(String username, String password) {
        Query checkUserDatabase = reference.orderByChild("username").equalTo(username);
        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String passwordFromDB = snapshot.child(username).child("password").getValue(String.class);
                    if (passwordFromDB.equals(password)) {
                        loginStatus.setValue("Login Successful");
                    } else {
                        loginStatus.setValue("Invalid Credentials");
                    }
                } else {
                    loginStatus.setValue("User does not exist");
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                loginStatus.setValue("Login Failed: " + error.getMessage());
            }
        });
    }
}
