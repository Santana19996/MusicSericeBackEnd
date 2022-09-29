package com.example.musicplayerbackend.service.song;


import com.example.musicplayerbackend.model.Song;
import org.springframework.http.ResponseEntity;


public interface SongService {
    ResponseEntity<?> getAllSongs();

    ResponseEntity<?> getSongById(int id);

    ResponseEntity<?> addSong(Song song);

    ResponseEntity<?> editSongById(int id, Song song);

    ResponseEntity<?> deleteSongById(int id);


}
