package com.example.musicplayerbackend.repository;

import com.example.musicplayerbackend.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Integer> {


}

