package com.example.demo.repository;

import com.example.demo.model.Reponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReponseRepository extends JpaRepository<Reponse, Integer> {
    List<Reponse> findByQuestion_Id(Long id);



}

