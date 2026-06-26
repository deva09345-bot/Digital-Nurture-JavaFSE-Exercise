package com.cognizant.ormlearn.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qu_id")
    private int id;

    @Column(name = "qu_text")
    private String text;

    @Column(name = "qu_score")
    private double score;

    @OneToMany(mappedBy = "question")
    private List<Option> optionList;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public double getScore() { return score; }
    public void setScore(double score) { this.score = score; }
    public List<Option> getOptionList() { return optionList; }
    public void setOptionList(List<Option> optionList) { this.optionList = optionList; }

    @Override
    public String toString() { return "Question{id=" + id + ", text='" + text + "'}"; }
}
