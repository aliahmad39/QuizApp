package com.ali.quizapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.fragment.app.Fragment;

import com.ali.quizapp.databinding.FragmentWalletBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;



public class WalletFragment extends Fragment {

    public WalletFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    FragmentWalletBinding binding;
    FirebaseFirestore database;
      User user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        binding=FragmentWalletBinding.inflate(inflater,container,false);
        database=FirebaseFirestore.getInstance();



        database.collection("Users")
                .document(FirebaseAuth.getInstance().getUid())
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()) {
                   user = documentSnapshot.toObject(User.class);
                    binding.currentCoins.setText(user.getCoins() + "");
                }else{
                    Toast.makeText(getContext(), "data is empty", Toast.LENGTH_SHORT).show();
                    user = new User();
                    binding.currentCoins.setText(user.getCoins() + "");
                }

            }
        });
        binding.sendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getCoins()>50000){
                   String uid= FirebaseAuth.getInstance().getUid();
                   String payPal=binding.emailBox.getText().toString();

                    WithdrawRequest request=new WithdrawRequest(uid,payPal,user.getName());
                    database.collection("withdraw")
                            .document(uid)
                            .set(request).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getContext(),"Request send successfully.",Toast.LENGTH_SHORT).show();
                        }
                    });

                }else {
                    Toast.makeText(getContext(),"you needs more coins to get withdraw",Toast.LENGTH_SHORT).show();
                }

            }
        });

        return binding.getRoot();
    }

}