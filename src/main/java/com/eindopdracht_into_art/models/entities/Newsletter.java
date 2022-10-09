package com.eindopdracht_into_art.models.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Newsletter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String subscription;

    public Newsletter(String fileName){
        this.fileName = fileName;
    }
    public Newsletter(String fileName, String absolutePath, String subscription) {
        this.fileName = fileName;
        this.subscription = subscription;
    }

    @OneToMany(mappedBy = "newsletter")
    private Set<NewsletterSubscriber> subscribers;


    protected Newsletter() {
    }

    //region Getters
    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public String getSubscription() {
        return subscription;
    }
    //endregion

    //region Setters
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    //endregion

}
