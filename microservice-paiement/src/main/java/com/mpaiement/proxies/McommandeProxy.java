package com.mpaiement.proxies;

import com.mpaiement.beans.CommandeBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@FeignClient(name = "zuul-server")
@RibbonClient(name = "microservice-commandes")
public interface McommandeProxy {

    @PutMapping(value = "/microservice-commandes/commandes")
    public CommandeBean modifierUneCommande(@RequestBody CommandeBean commande);

    @GetMapping(value = "/microservice-commandes/commandes/{id}")
    public CommandeBean recupererUneCommande(@PathVariable(value="id") int id);

    @PutMapping(value = "/microservice-commandes/commandes/payee/{id}")
    public CommandeBean payerUneCommande(@PathVariable(value="id") int id);

}
