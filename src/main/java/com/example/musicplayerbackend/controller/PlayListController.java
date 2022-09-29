package com.example.musicplayerbackend.controller;

import com.example.musicplayerbackend.model.Playlist;
import com.example.musicplayerbackend.model.Song;
import com.example.musicplayerbackend.model.User;
import com.example.musicplayerbackend.service.playlist.PlayListService;
import com.example.musicplayerbackend.service.user.UserService;
import com.example.musicplayerbackend.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlayListController {
    @Autowired
    private UserService userService;
    @Autowired
    private PlayListService playListService;
    @Autowired
    private JwtTokenUtil jwtUtil;

    @PostMapping("/createplaylist")
    public ResponseEntity<?> createPlaylist(@RequestHeader(name = "Authorization") String token, @RequestBody Playlist playlist) {
        String jwtToken = token.replace("Bearer ", "");
        User userToAdd = jwtUtil.extractUser(jwtToken);
        playlist.setPlaylistOwner(userToAdd);
        return playListService.createPlaylist(playlist, userToAdd);
    }


    @PostMapping("/addsongtoplaylist/{playlistId}")
    public ResponseEntity<?> addSongToPlaylist(@RequestHeader(name = "Authorization") String token, @RequestBody Song song, @PathVariable("playlistId") int playlistId) {
        String jwtToken = token.replace("Bearer ", "");
        User userToAdd = jwtUtil.extractUser(jwtToken);
        boolean userOwnsPlaylist = false;
        for (Playlist var : userToAdd.getPlaylists()) {
            if (var.getId() == playlistId) {
                userOwnsPlaylist = true;
                break;
            }
        }
        System.out.println(userToAdd.getPlaylists().size());
        if (!userOwnsPlaylist) {
            return new ResponseEntity<>("You Dont own this playlist", HttpStatus.BAD_REQUEST);
        }
        return playListService.addSongToPlaylist(song.getId(), playlistId);
    }

    @DeleteMapping("/deletesongfromplaylist/{playlistId}/{songId}")
    public ResponseEntity<?> deleteSongFromPlaylist(@RequestHeader(name = "Authorization") String token, @PathVariable("playlistId") int playlistId, @PathVariable("songId") int songId) {
        String jwtToken = token.replace("Bearer ", "");
        User userToAdd = jwtUtil.extractUser(jwtToken);
        boolean userOwnsPlaylist = false;
        for (Playlist var : userToAdd.getPlaylists()) {
            if (var.getId() == playlistId) {
                userOwnsPlaylist = true;
                break;
            }
        }
        if (!userOwnsPlaylist) {
            return new ResponseEntity<>("You Dont own this playlist", HttpStatus.BAD_REQUEST);
        }
        playListService.deleteSongFromPlaylist(playlistId,songId);
        return new ResponseEntity<>("Song deleted from playlist", HttpStatus.ACCEPTED);
    }

}
