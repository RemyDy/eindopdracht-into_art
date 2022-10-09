package com.eindopdracht_into_art.models.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Artist {

    @Id
    @Column(name = "user_account_id")
    private Long id;
    @Column(nullable = false)
    private String forename;
    @Column(nullable = false)
    private String surname;
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_account_id")
    private UserAccount account;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "artist_artwork"
            , joinColumns = @JoinColumn(name = "artist_id")
            , inverseJoinColumns = @JoinColumn(name = "artwork_id"))
    private Set<Artwork> artworks;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "artist_newsletter"
            , joinColumns = @JoinColumn(name = "artist_id")
            , inverseJoinColumns = @JoinColumn(name = "newsletter_id"))
    private Set<Newsletter> newsletters;

    public void addArtwork(Artwork.Builder artworks) {
        this.artworks.add(artworks.build());
    }

    public void addNewsletter(Newsletter newsletter){
        this.newsletters.add(newsletter);
    }

    //region Getters
    public Long getId() {
        return id;
    }

    public String getForename() {
        return forename;
    }

    public String getSurname() {
        return surname;
    }

    public UserAccount getAccount() {
        return account;
    }

    public Set<Artwork> getArtworks() {
        return artworks;
    }

    public Set<Newsletter> getNewsletters() {
        return newsletters;
    }

    //endregion

}
