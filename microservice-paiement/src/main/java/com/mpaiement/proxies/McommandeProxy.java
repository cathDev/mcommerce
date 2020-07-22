package com.mpaiement.proxies;

import com.mpaiement.beans.CommandeBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@FeignClient(name = "microservice-commandes", url="http://localhost:9002")
public interface McommandeProxy {

    @PutMapping(value = "/commandes")
    public CommandeBean modifierUneCommande(@RequestBody CommandeBean commande);

    @GetMapping(value = "/commandes/{id}")
    public CommandeBean recupererUneCommande(@PathVariable int id);

}
