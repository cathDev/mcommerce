package com.expedition.microserviceexpedition.web.controller;


import com.expedition.microserviceexpedition.dao.ExpeditionDao;
import com.expedition.microserviceexpedition.model.Expedition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ExpeditionController {

    @Autowired
    private ExpeditionDao expeditionDao;

    @RequestMapping(value = "/expeditions", method = RequestMethod.POST)
    public Expedition ajouterUneExpedition(@RequestBody Expedition expedition){
        return expeditionDao.save(expedition);
    }

    @RequestMapping(value="/expeditions/{id}", method = RequestMethod.GET)
    public Expedition getOneExpedition(@PathVariable int id){

        return expeditionDao.getExpeditionByIdCommande(id);
    }

    @RequestMapping(value="/expeditions/findById/{id}", method = RequestMethod.GET)
    public Expedition getExpeditionById(@PathVariable int id){

        return expeditionDao.findById(id).orElse(null);
    }

    @RequestMapping(value="/expeditions", method = RequestMethod.PUT)
    public Expedition updateExpedition(@RequestBody Expedition expedition){
        return expeditionDao.save(expedition);
    }


}
