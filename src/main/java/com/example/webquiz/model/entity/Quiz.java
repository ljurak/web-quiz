package com.example.webquiz.model.entity;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User author;

    @ElementCollection
    @CollectionTable(name = "OPTIONS")
    @Column(name = "OPTION")
    private List<String> options;

    private int[] answer;

    public Quiz() {
    }

    public Quiz(String title, String text, User author, List<String> options, int[] answer) {
        this.title = title;
        this.text = text;
        this.author = author;
        this.options = options;
        this.answer = answer;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public User getAuthor() {
        return author;
    }

    public List<String> getOptions() {
        return options;
    }

    public int[] getAnswer() {
        return answer;
    }
}
