package com.spa.demo.backend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CupboardRepository extends JpaRepository<Cupboard, Long> {
}
