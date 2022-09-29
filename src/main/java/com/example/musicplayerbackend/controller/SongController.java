package com.example.musicplayerbackend.controller;


import com.example.musicplayerbackend.model.Song;
import com.example.musicplayerbackend.service.song.SongService;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class SongController {
    @Autowired
    private SongService songService;

    @GetMapping(value = "/songs")
    public ResponseEntity<?> getAllSongsHandler() {
        return songService.getAllSongs();
    }

    @GetMapping(value = "/songs/{songId}")
    public ResponseEntity<?> getSongByIdHandler(@PathVariable("songId") int id) {
        return songService.getSongById(id);
    }

    @PostMapping("/addsong")
    public ResponseEntity<?> addSongHandler(@RequestBody Song song) {
        return songService.addSong(song);
    }

    @PutMapping("/editsong/{songId}")
    public ResponseEntity<?> editSongByIdHandler(@PathVariable("songId") int id, @RequestBody Song song) {
        return songService.editSongById(id, song);
    }

    @DeleteMapping("/deletesong/{songId}")
    public ResponseEntity<?> deleteSongByIdHandler(@PathVariable("songId") int id) {
        return songService.deleteSongById(id);
    }
}
