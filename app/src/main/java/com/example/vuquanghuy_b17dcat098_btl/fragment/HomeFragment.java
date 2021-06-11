package com.example.vuquanghuy_b17dcat098_btl.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vuquanghuy_b17dcat098_btl.CategoryAdapter;
import com.example.vuquanghuy_b17dcat098_btl.databinding.FragmentHomeBinding;
import com.example.vuquanghuy_b17dcat098_btl.model.Category;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class HomeFragment extends Fragment {


    public HomeFragment() {
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    FragmentHomeBinding binding;
    FirebaseFirestore database;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentHomeBinding.inflate(inflater,container,false);
        database= FirebaseFirestore.getInstance();
        ArrayList<Category> categories= new ArrayList<>();
        CategoryAdapter adapter=new CategoryAdapter(getContext(),categories);
        database
                .collection("categories")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        categories.clear();
                        for(DocumentSnapshot snapshot:value.getDocuments()){
                            Category category=snapshot.toObject(Category.class);
                            category.setId(snapshot.getId());
                            categories.add(category);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
        binding.categoryList.setLayoutManager(new GridLayoutManager(getContext(),2));
        binding.categoryList.setAdapter(adapter);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}