package com.clientui.controller;

import com.clientui.beans.CommandeBean;
import com.clientui.beans.ExpeditionBean;
import com.clientui.beans.PaiementBean;
import com.clientui.beans.ProductBean;
import com.clientui.proxies.MicroserviceCommandesProxy;
import com.clientui.proxies.MicroserviceExpeditionProxy;
import com.clientui.proxies.MicroservicePaiementProxy;
import com.clientui.proxies.MicroserviceProduitsProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


@Controller
public class ClientController {

    @Autowired
    private MicroserviceProduitsProxy ProduitsProxy;

    @Autowired
    private MicroserviceCommandesProxy CommandesProxy;

    @Autowired
    private MicroservicePaiementProxy paiementProxy;

    @Autowired
    private MicroserviceExpeditionProxy microserviceExpeditionProxy;

    @RequestMapping("/")
    public String accueil(Model model){

        List<ProductBean> produits =  ProduitsProxy.listeDesProduits();

        model.addAttribute("produits", produits);

        return "Accueil";
    }

    @RequestMapping("/details-produit/{id}")
    public String detail(@PathVariable int id,  Model model){

        ProductBean produit =  ProduitsProxy.recupererUnProduit(id);

        model.addAttribute("produit", produit);

        return "DetailProduit";
    }


    @RequestMapping(value = "/commander-produit/{idProduit}/{montant}")
    public String passerCommande(@PathVariable int idProduit, @PathVariable Double montant,  Model model){


        CommandeBean commande = new CommandeBean();

        //On renseigne les propriétés de l'objet de type CommandeBean que nous avons crée
        commande.setProductId(idProduit);
        commande.setQuantite(1);
        commande.setDateCommande(new Date());

        //appel du microservice commandes grâce à Feign et on récupère en retour les détails de la commande créée, notamment son ID (étape 4).
        CommandeBean commandeAjoutee = CommandesProxy.ajouterCommande(commande);

        //on passe à la vue l'objet commande et le montant de celle-ci afin d'avoir les informations nécessaire pour le paiement
        model.addAttribute("commande", commandeAjoutee);
        model.addAttribute("montant", montant);

        return "Paiement";
    }


    @RequestMapping(value = "payer-commande/{idCommande}/{montant}")
    public String payerCommande(@PathVariable int idCommande, @PathVariable Double montant,  Model model){

        PaiementBean paiementAExcecuter = new PaiementBean();

        //On renseigne les propriétés de l'objet de type PaiementBean que nous avons crée
        paiementAExcecuter.setIdCommande(idCommande);
        paiementAExcecuter.setMontant(montant);
        paiementAExcecuter.setNumeroCarte(numcarte());


        ResponseEntity<PaiementBean> paiement = paiementProxy.payerUneCommande(paiementAExcecuter);

        Boolean paiementAccepte = false;
        //si le code est autre que 201 CREATED, c'est que le paiement n'a pas pu aboutir.
        if(paiement.getStatusCode() == HttpStatus.CREATED)
            paiementAccepte = true;

        model.addAttribute("paiementOk", paiementAccepte); // on envoi un Boolean paiementOk à la vue

        return "confirmation";
    }

    @RequestMapping(value="suivi/{id}")
    public String getOneExpedition(@PathVariable int id, Model model){
        ExpeditionBean expedition = microserviceExpeditionProxy.getOneExpedition(id);
        System.out.println("voici l'expédition "+expedition.toString());
        model.addAttribute("expedition",expedition);
        return "expedition";
    }

    private Long numcarte() {

        return ThreadLocalRandom.current().nextLong(1000000000000000L,9000000000000000L );
    }



}