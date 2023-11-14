package com.springboot3crud.repository;


import com.springboot3crud.entity.AppUser;
import com.springboot3crud.entity.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Notes, Long> {

    Optional<Notes> findByAppUserAndTitle(AppUser appUser, String title);

    Optional<Notes> findByAppUser(AppUser appUser);
}
