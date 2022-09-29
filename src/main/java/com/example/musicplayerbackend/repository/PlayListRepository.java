package com.example.musicplayerbackend.repository;

import com.example.musicplayerbackend.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayListRepository extends JpaRepository<Playlist, Integer> {

}

