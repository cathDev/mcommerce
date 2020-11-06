package com.mcommandes.dao;

import com.mcommandes.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CommandesDao extends JpaRepository<Commande, Integer> {

    @Transactional
    @Modifying
    @Query("Update Commande c set c.commandePayee = true where c.id = ?1")
    int payerUneCommande(int id);
}
