package com.mcommandes.web.controller;


import com.mcommandes.dao.CommandesDao;
import com.mcommandes.model.Commande;
import com.mcommandes.web.exceptions.CommandeNotFoundException;
import com.mcommandes.web.exceptions.ImpossibleAjouterCommandeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CommandeController {

    private static final Logger logger = LoggerFactory.getLogger(CommandeController.class);

    @Autowired
    CommandesDao commandesDao;

    @PostMapping (value = "/commandes")
    public ResponseEntity<Commande> ajouterCommande(@RequestBody Commande commande){

        Commande nouvelleCommande = commandesDao.save(commande);

        if(nouvelleCommande == null){
            logger.error("Impossible d'ajouter cette commande");
            throw new ImpossibleAjouterCommandeException("Impossible d'ajouter cette commande");
        }

        return new ResponseEntity<Commande>(commande, HttpStatus.CREATED);
    }

    @GetMapping(value = "/commandes/{id}")
    public Optional<Commande> recupererUneCommande(@PathVariable int id){

        Optional<Commande> commande = commandesDao.findById(id);

        if(!commande.isPresent()){
            logger.error("Cette commande n'existe pas");
            throw new CommandeNotFoundException("Cette commande n'existe pas");
        }

        return commande;
    }

    @PutMapping(value = "/commandes")
    public Commande modifierUneCommande(@RequestBody Commande commande){

        Commande commandeModifie = commandesDao.save(commande);

        return commandeModifie;
    }
}
