package com.clientui.proxies;

import com.clientui.beans.PaiementBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/*
feignClient before including ribbon
@FeignClient(name = "microservice-paiements", url = "http://localhost:9003")
*/
@FeignClient(contextId ="microservicePaiementProxy", name = "zuul-server")
@RibbonClient(name = "microservice-paiements")
public interface MicroservicePaiementProxy {

    @PostMapping(value = "/microservice-paiements/paiement")
    ResponseEntity<PaiementBean> payerUneCommande(@RequestBody PaiementBean paiement);

}
