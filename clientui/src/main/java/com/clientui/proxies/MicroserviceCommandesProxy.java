package com.clientui.proxies;

import com.clientui.beans.CommandeBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/*
feignClient before including ribbon
@FeignClient(name = "microservice-commandes", url = "http://localhost:9002")
*/
@FeignClient(contextId ="microserviceCommandesProxy", name = "zuul-server")
@RibbonClient(name = "microservice-commandes")
public interface MicroserviceCommandesProxy {

    @PostMapping(value = "/microservice-commandes/commandes")
    CommandeBean ajouterCommande(@RequestBody CommandeBean commande);

   /* @GetMapping(value = "/commandes/{id}")
    public CommandeBean recupererUneCommande(@PathVariable int id);*/
}
