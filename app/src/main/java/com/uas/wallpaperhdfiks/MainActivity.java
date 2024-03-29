package com.uas.wallpaperhdfiks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.uas.wallpaperhdfiks.CategoryViewHolder.CategoryViewHolder;
import com.uas.wallpaperhdfiks.Model.CategoryItem;
import com.uas.wallpaperhdfiks.ut.Ut;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference categoryReference;
    FirebaseRecyclerOptions<CategoryItem> options;
    FirebaseRecyclerAdapter<CategoryItem, CategoryViewHolder> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        categoryReference = FirebaseDatabase.getInstance().getReference().child("Category");

        options = new FirebaseRecyclerOptions.Builder<CategoryItem>()
                .setQuery(categoryReference,CategoryItem.class).build();

        adapter = new FirebaseRecyclerAdapter<CategoryItem, CategoryViewHolder>(options) {
            @Override
            protected void onBindViewHolder(final CategoryViewHolder holder, final int position, final CategoryItem model) {



                Picasso.get().load(model.getImage())
                        .networkPolicy(NetworkPolicy.OFFLINE)
                        .into(holder.imageView, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        Picasso.get().load(model.getImage())
                                .error(R.drawable.ic_autorenew_black_24dp)
                                .into(holder.imageView);

                    }
                });

                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Ut.CATEGORY_ID = adapter.getRef(position).getKey();
                        Ut.CATEGORY_SELECTED = model.getName();

                        Intent i = new Intent(MainActivity.this,ListWallpaperActivity.class);
                        startActivity(i);
                    }
                });


            }

            @NonNull
            @Override
            public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.layout_category_item,parent,false);


                return new CategoryViewHolder(v);
            }
        };


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(adapter!=null)
        {
            adapter.startListening();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(adapter!=null)
        {
            adapter.startListening();
        }
    }

    @Override
    protected void onStop() {
        if(adapter!=null)
        {
            adapter.stopListening();
        }

        super.onStop();


    }
}
