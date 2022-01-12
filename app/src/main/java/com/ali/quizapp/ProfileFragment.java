package com.ali.quizapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ali.quizapp.databinding.FragmentProfileBinding;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.util.Calendar;


public class ProfileFragment extends Fragment {
    private Button updateBtn;
    private Button aboutBtn;
    String passBox, emailBox, nameBox, profileImage;
    User user;
    Uri selectedimage;
    FirebaseStorage storage;
    String filepath;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    FragmentProfileBinding binding;
    FirebaseFirestore database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        database = FirebaseFirestore.getInstance();
        // Inflate the layout for this fragment

        storage =FirebaseStorage.getInstance();

        binding.profileImage.setImageURI(selectedimage);

        database.collection("Users")
                .document(FirebaseAuth.getInstance().getUid())
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    user = documentSnapshot.toObject(User.class);
                    binding.emailBox.setText(user.getEmail());
                    binding.nameBox.setText(user.getName());
                    binding.passBox.setText(user.getPass());
                    Glide.with(getContext()).load(user.getProfileImage()).placeholder(R.drawable.avatar).into(binding.profileImage);
                }

            }
        });

        binding.profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 48);
            }
        });


        binding.updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                database.collection("Users")
                        .document(FirebaseAuth.getInstance().getUid())
                        .update("name", binding.nameBox.getText().toString());
                database.collection("Users")
                        .document(FirebaseAuth.getInstance().getUid())
                        .update("email", binding.emailBox.getText().toString());
                database.collection("Users")
                        .document(FirebaseAuth.getInstance().getUid())
                        .update("pass", binding.passBox.getText().toString());
                database.collection("Users")
                        .document(FirebaseAuth.getInstance().getUid())
                        .update("profileImage", filepath+"");


                Toast.makeText(getContext(), "profile Update Successfully", Toast.LENGTH_SHORT).show();
            }
        });
        binding.aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),aboutUs.class));

            }
        });

        return binding.getRoot();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Calendar calendar = Calendar.getInstance();
        if (requestCode == 48) {
            if (data != null) {
                selectedimage = data.getData();
                final StorageReference reference = storage.getReference().child("PofileImages").child(calendar.getTimeInMillis() + "");

                    reference.putFile(selectedimage).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if (task.isSuccessful()){
                               reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                   @Override
                                   public void onSuccess(Uri uri) {
                                     filepath = uri.toString();
                                   }
                               });
                            }
                        }
                    });
            }

        }
    }
}
