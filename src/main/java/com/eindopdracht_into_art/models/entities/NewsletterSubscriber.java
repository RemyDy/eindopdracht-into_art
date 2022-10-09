package com.eindopdracht_into_art.models.entities;

import javax.persistence.*;

@Entity
class NewsletterSubscriber {

    @EmbeddedId
    NewsletterSubscriberKey id;
    String subscription;
    String subscriberEmail;

    @ManyToOne
    @MapsId("newsletterId")
    @JoinColumn(name = "newsletter_id")
    Newsletter newsletter;

    @ManyToOne
    @MapsId("subscriberId")
    @JoinColumn(name = "subscriber_id")
    Subscriber subscriber;

    //region Getters
    public String getSubscription() {
        return subscription;
    }

    public String getSubscriberEmail() {
        return subscriberEmail;
    }

    public Newsletter getNewsletter() {
        return newsletter;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }
    //endregion

    //region Setters
    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    public void setId(NewsletterSubscriberKey id) {
        this.id = id;
    }

    public void setNewsletter(Newsletter newsletter) {
        this.newsletter = newsletter;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public void setSubscriberEmail(String subscriberEmail) {
        this.subscriberEmail = subscriberEmail;
    }

    //endregion
}
