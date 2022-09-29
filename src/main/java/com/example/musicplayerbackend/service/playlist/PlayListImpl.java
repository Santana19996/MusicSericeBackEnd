package com.example.musicplayerbackend.service.playlist;

import com.example.musicplayerbackend.model.Playlist;
import com.example.musicplayerbackend.model.Song;
import com.example.musicplayerbackend.model.User;
import com.example.musicplayerbackend.repository.PlayListRepository;
import com.example.musicplayerbackend.repository.SongRepository;
import com.example.musicplayerbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PlayListImpl implements PlayListService {
    @Autowired
    private SongRepository songRepository;
    @Autowired
    private PlayListRepository playListRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public ResponseEntity<?> createPlaylist(Playlist playlist, User currentUser) {
        Optional<Playlist> optional = playListRepository.findById(playlist.getId());
        if (optional.isPresent()) {
            return new ResponseEntity<>("Playlist with the id already present", HttpStatus.BAD_REQUEST);
        }
        Playlist playlistIndDB = playListRepository.save(playlist);

        System.out.println(playlistIndDB);

        List<Playlist> currentPlaylists = currentUser.getPlaylists();

        if (currentPlaylists == null) {
            currentPlaylists = new ArrayList<Playlist>();
        }

        currentPlaylists.add(playlistIndDB);

        currentUser.setPlaylists(currentPlaylists);

        System.out.println(userRepository);
        System.out.println(currentUser);

        User newBoi = userRepository.getById(currentUser.getUserId());

        newBoi.setPlaylists(currentPlaylists);


        userRepository.save(newBoi);

        return new ResponseEntity<>("Playlist Created", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> addSongToPlaylist(int songId, int playlistId) {
        Song song = new Song();
        song.setId(songId);
        Optional<Playlist> optional = playListRepository.findById(playlistId);
        if (optional.isPresent()) {
            Playlist playlist = optional.get();
            playlist.getSongs().add(song);
            playListRepository.save(playlist);
            return new ResponseEntity<>("Song added to playlist", HttpStatus.OK);
        }
        return new ResponseEntity<>("Playlist with the id not present", HttpStatus.BAD_REQUEST);
    }

    @Override
    public void deleteSongFromPlaylist(int playlistId, int songId) {
        Song song = new Song();
        song.setId(songId);
        Optional<Playlist> optional = playListRepository.findById(playlistId);
        if (optional.isPresent()) {
            Playlist playlist = optional.get();
            List<Song> songsToCheck = playlist.getSongs();

            songsToCheck.removeIf(songToRemove -> songToRemove.getId() == songId);
            playlist.setSongs(songsToCheck);
            playListRepository.save(playlist);
        }
    }
}

