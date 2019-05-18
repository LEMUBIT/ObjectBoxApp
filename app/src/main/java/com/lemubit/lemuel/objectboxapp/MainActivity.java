package com.lemubit.lemuel.objectboxapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import io.objectbox.Box;

public class MainActivity extends AppCompatActivity {
    EditText eTxtTask;
    EditText eTxtPriority;
    Button btnAdd;
    EditText eTxtSearchByPriority;
    Button btnSearch;
    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eTxtTask = findViewById(R.id.eTxtTask);
        eTxtPriority = findViewById(R.id.eTxtPriority);
        btnAdd = findViewById(R.id.btnSave);
        eTxtSearchByPriority = findViewById(R.id.eTxtProritySearch);
        btnSearch = findViewById(R.id.btnSearch);
        txtResult = findViewById(R.id.txtSearchResult);

        Box<Task> taskBox = ObjectBox.get().boxFor(Task.class);

        btnAdd.setOnClickListener(v -> {
            String task = eTxtTask.getText().toString();
            int priority = Integer.valueOf(eTxtPriority.getText().toString());
            Task task1 = new Task();

            task1.priority = priority;
            task1.task = task;

            taskBox.put(task1);
        });


        btnSearch.setOnClickListener(v -> {
            int priority = Integer.valueOf(eTxtSearchByPriority.getText().toString());
            List<Task> tasks = taskBox.query().equal(Task_.priority, priority).build().find();
            StringBuilder stringBuilder = new StringBuilder();

            for (Task x : tasks) {
                stringBuilder.append(x.task);
            }

            txtResult.setText(stringBuilder.toString());
        });
    }
}
