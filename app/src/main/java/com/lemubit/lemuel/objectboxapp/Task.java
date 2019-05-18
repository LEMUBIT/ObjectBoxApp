package com.lemubit.lemuel.objectboxapp;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Task {
    @Id
    public long id;
    public String task;
    public int priority;

}
