package com.expedition.microserviceexpedition.dao;


import com.expedition.microserviceexpedition.model.Expedition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpeditionDao extends JpaRepository<Expedition, Integer> {

    @Query("select e from Expedition e where e.idCommande = ?1")
    Expedition getExpeditionByIdCommande(int id);
}
