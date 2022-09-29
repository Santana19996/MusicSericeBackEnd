package com.example.musicplayerbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    @ManyToMany(mappedBy = "songs")
    @JsonBackReference
    private List<Playlist> playlists;
    private String title;
    private String artist;
    private String url;
    private String album;
    private String genre;
    private String albumArt;
}
