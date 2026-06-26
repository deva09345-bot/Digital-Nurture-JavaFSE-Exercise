package com.cognizant.ormlearn.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "attempt_question")
public class AttemptQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aq_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "aq_at_id")
    private Attempt attempt;

    @ManyToOne
    @JoinColumn(name = "aq_qu_id")
    private Question question;

    @OneToMany(mappedBy = "attemptQuestion")
    private List<AttemptOption> attemptOptionList;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Attempt getAttempt() { return attempt; }
    public void setAttempt(Attempt attempt) { this.attempt = attempt; }
    public Question getQuestion() { return question; }
    public void setQuestion(Question question) { this.question = question; }
    public List<AttemptOption> getAttemptOptionList() { return attemptOptionList; }
    public void setAttemptOptionList(List<AttemptOption> list) { this.attemptOptionList = list; }
}
