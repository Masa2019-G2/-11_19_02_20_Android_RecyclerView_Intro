package com.telran.a19_02_20_part2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements MyAdapter.OnRowClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MyAdapter adapter = new MyAdapter(this);
        RecyclerView rv = findViewById(R.id.my_rv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        rv.setAdapter(adapter);
        rv.setLayoutManager(layoutManager);
        RecyclerView.ItemDecoration divider = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        rv.addItemDecoration(divider);
        Button addBtn = findViewById(R.id.addBtn);
        Button removeBtn = findViewById(R.id.rmBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.add(new Person("New Person","2312341"));
            }
        });

        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.remove();
            }
        });
    }

    @Override
    public void onClick(int position, Person p) {
        Log.d("MY_TAG", "onClick() called with: position = [" + position + "], p = [" + p + "]");
    }
}
