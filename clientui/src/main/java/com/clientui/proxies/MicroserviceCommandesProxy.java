package com.clientui.proxies;

import com.clientui.beans.CommandeBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "microservice-commandes", url="http://localhost:9002")
public interface MicroserviceCommandesProxy {

    @PostMapping(value = "/commandes")
    public CommandeBean ajouterCommande(@RequestBody CommandeBean commande);

    @GetMapping(value = "/commandes/{id}")
    public CommandeBean recupererUneCommande(@PathVariable int id);
}
