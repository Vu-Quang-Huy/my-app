package com.example.vuquanghuy_b17dcat098_btl.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vuquanghuy_b17dcat098_btl.R;
import com.example.vuquanghuy_b17dcat098_btl.databinding.FragmentProfileBinding;
import com.example.vuquanghuy_b17dcat098_btl.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class ProfileFragment extends Fragment {




    public ProfileFragment() {

    }



    FirebaseFirestore database;
    User user;
    FragmentProfileBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater,container,false);
        database=FirebaseFirestore.getInstance();

        database
                .collection("users")
                .document(FirebaseAuth.getInstance().getUid())
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                user = documentSnapshot.toObject(User.class);
                binding.edEmail.setText(user.getEmail());
                binding.edName.setText(user.getName());
                binding.edPAss.setText(user.getPass());
            }
        });

        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uid=FirebaseAuth.getInstance().getUid();
                String email=binding.edEmail.getText().toString();
                String name=binding.edName.getText().toString();
                String pass=binding.edPAss.getText().toString();
                User user= new User(name,email,pass);
                database.collection("users")
                        .document(uid)
                        .set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getContext(),"Update successful!",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        return binding.getRoot();
    }
}