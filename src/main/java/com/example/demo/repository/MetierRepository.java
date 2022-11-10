package com.example.demo.repository;

import com.example.demo.model.Metier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetierRepository extends JpaRepository<Metier, Integer> {
}