package com.spa.demo.backend;

import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IdentificationRepository extends JpaRepository<Identification, String> {
    List<Identification> findByPersonId(String personId);

    @Transactional
    @Modifying
    @Query("Update Identification identification set identification.money = :money where identification.personId = :personId")
    void updateByPersonId(String personId, int money);

    void deleteByPersonId(String customerId);
}

