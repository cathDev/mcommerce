package com.mpaiement.web.controller;

import com.mpaiement.dao.PaiementDao;
import com.mpaiement.model.Paiement;
import com.mpaiement.proxies.McommandeProxy;
import com.mpaiement.web.exceptions.PaiementExistantException;
import com.mpaiement.web.exceptions.PaiementImpossibleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaiementController {

    private static final Logger logger = LoggerFactory.getLogger(PaiementController.class);

    @Autowired
    private PaiementDao paiementDao;

    @Autowired
    private McommandeProxy mcommandeProxy;

    @PostMapping(value = "/paiement")
    public ResponseEntity<Paiement>  payerUneCommande(@RequestBody Paiement paiement){


        //Vérifions s'il y a déjà un paiement enregistré pour cette commande
        Paiement paiementExistant = paiementDao.findByidCommande(paiement.getIdCommande());
        if(paiementExistant != null) {
            logger.error("Cette commande est déjà payée");
            throw new PaiementExistantException("Cette commande est déjà payée");
        }

        //Enregistrer le paiement
        Paiement nouveauPaiement = paiementDao.save(paiement);


        if(nouveauPaiement == null){
            logger.error("Erreur, impossible d'établir le paiement, réessayez plus tard");
            throw new PaiementImpossibleException("Erreur, impossible d'établir le paiement, réessayez plus tard");
        }

        //TODO Nous allons appeler le Microservice Commandes ici pour lui signifier que le paiement est accepté

        System.out.println("voici l'id de commande payée: "+paiement.getIdCommande());
        mcommandeProxy.payerUneCommande(paiement.getIdCommande());


        return new ResponseEntity<Paiement>(nouveauPaiement, HttpStatus.CREATED);

    }




}
