package com.example.musicplayerbackend.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String playlistName;
    @ManyToOne
    @JsonBackReference
    private User playlistOwner;

    @ManyToMany
    @JoinTable(
            name="playlist_song",
            joinColumns={@JoinColumn(name="playlist_id")},
            inverseJoinColumns={@JoinColumn(name="song_id")}
    )
    @JsonIgnore
    @JsonManagedReference
    private List<Song> songs;

}
