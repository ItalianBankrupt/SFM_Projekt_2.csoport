package com.spa.demo.backend;

import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentificationRepository extends JpaRepository<Identification, String> {
    @Transactional
    @Modifying
    @Query("update Identification  i set i.money = :money where i.PersonId = :personID")
    int updateMoneyForPerson(int money, String personId);
}
