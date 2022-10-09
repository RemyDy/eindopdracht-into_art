package com.eindopdracht_into_art.models.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
class NewsletterSubscriberKey implements Serializable {

    @Column(name = "newsletter_id")
    Long newsletterId;
    @Column(name = "subscriber_id")
    Long subscriberId;

    //region Getters
    public Long getNewsletterId() {
        return newsletterId;
    }

    public Long getSubscriberId() {
        return subscriberId;
    }
    //endregion

    //region Setters
    public void setNewsletterId(Long newsletterId) {
        this.newsletterId = newsletterId;
    }

    public void setSubscriberId(Long subscriberId) {
        this.subscriberId = subscriberId;
    }
    //endregion

    //region Equals & HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewsletterSubscriberKey that)) return false;
        return getNewsletterId().equals(that.getNewsletterId()) && getSubscriberId().equals(that.getSubscriberId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNewsletterId(), getSubscriberId());
    }
    //endregion
}
