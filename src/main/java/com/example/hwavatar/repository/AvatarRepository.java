package com.example.hwavatar.repository;

import com.example.hwavatar.model.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar, Long> {

    Avatar findByFacultyId(long id);
}
