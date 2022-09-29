package com.example.musicplayerbackend.service.song;

import com.example.musicplayerbackend.model.Song;
import com.example.musicplayerbackend.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {
    @Autowired
    private SongRepository songRepository;


    @Override
//    ? Dont know the generic type
    public ResponseEntity<?> getAllSongs() {
        Optional<List<Song>> optional = Optional.of(songRepository.findAll());
        if (optional.get().isEmpty()) {
            return ResponseEntity.ok("No Songs Available");
        }
        return ResponseEntity.ok(optional.get());
    }

    @Override
    public ResponseEntity<?> getSongById(int id) {
        Optional<Song> optional = songRepository.findById(id);
        if (optional.isPresent()) {
        ResponseEntity<?> response =  ResponseEntity.ok(optional.get());
        }
        return ResponseEntity.ok("No Song Available with that ID");
    }

    public ResponseEntity<?> addSong(Song song) {
        return ResponseEntity.ok(songRepository.save(song));
    }

    @Override
    public ResponseEntity<?> editSongById(int id, Song song) {
        Optional<Song> optional = songRepository.findById(id);
        if (optional.isPresent()) {
            Song song1 = optional.get();
            song1.setTitle(song.getTitle());
            song1.setArtist(song.getArtist());
            song1.setUrl(song.getUrl());
            song1.setAlbum(song.getAlbum());
            song1.setGenre(song.getGenre());
            song1.setAlbumArt(song.getAlbumArt());
            return ResponseEntity.ok(songRepository.save(song1));
        }
        return ResponseEntity.ok("No Song Available with that ID");

    }

    @Override
    public ResponseEntity<?> deleteSongById(int id) {
        Optional<Song> optional = songRepository.findById(id);
        if (optional.isPresent()) {
            songRepository.deleteById(id);
            return ResponseEntity.ok("Song Deleted");
        }
        return ResponseEntity.ok("No Song Available with that ID");
    }
}

