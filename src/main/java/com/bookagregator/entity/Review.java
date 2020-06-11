package com.bookagregator.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;

    @Column(columnDefinition="TEXT")
    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    private String filename;

    public Review() {
    }

    public Review(String title, String text, User user) {
        this.author = user;
        this.title = title;
        this.text = text;
    }

    public String getAuthorName(){
        return author.getUsername();
    }
    
}
