package com.cognizant.ormlearn.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "us_id")
    private int id;

    @Column(name = "us_name")
    private String name;

    @OneToMany(mappedBy = "user")
    private List<Attempt> attemptList;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Attempt> getAttemptList() { return attemptList; }
    public void setAttemptList(List<Attempt> attemptList) { this.attemptList = attemptList; }

    @Override
    public String toString() { return "User{id=" + id + ", name='" + name + "'}"; }
}
