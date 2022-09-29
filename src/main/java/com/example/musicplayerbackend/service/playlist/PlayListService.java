package com.example.musicplayerbackend.service.playlist;

import com.example.musicplayerbackend.model.Playlist;
import com.example.musicplayerbackend.model.User;
import org.springframework.http.ResponseEntity;

public interface PlayListService {

    ResponseEntity<?> createPlaylist(Playlist playlist, User currentUser);

    ResponseEntity<?> addSongToPlaylist(int songId, int playlistId);

    void deleteSongFromPlaylist(int playlistId ,int songId);

}


