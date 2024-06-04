package com.example.progkorny1.controller;

import com.example.progkorny1.model.Konyv;
import com.example.progkorny1.repo.KonyvRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class KonyvController {


@Autowired
    private KonyvRepo konyvRepo;
@GetMapping("/getAllKonyvek")

public ResponseEntity<List<Konyv>> getAllKonyvek() {

    try {
    List<Konyv> konyvLista = new ArrayList<>();
    konyvRepo.findAll().forEach(konyvLista::add);

    if (konyvLista.isEmpty()) {
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(konyvLista, HttpStatus.OK);
    }
    catch (Exception ex) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
@GetMapping("/getKonyvByAzon/{azon}")

public ResponseEntity<Konyv> getKonyvByAzon(@PathVariable Long azon) {

 Optional<Konyv> konyvData= konyvRepo.findById(azon);

 if (konyvData.isPresent()) {
     return new ResponseEntity<>(konyvData.get(), HttpStatus.OK);
 }
 return new ResponseEntity<>(HttpStatus.NOT_FOUND);

}
@PostMapping("/addKonyv")

public ResponseEntity<Konyv> addKonyv(@RequestBody Konyv konyv) {
 Konyv konyvObj= konyvRepo.save(konyv);

  return new ResponseEntity<>(konyvObj, HttpStatus.OK);
}

@PostMapping("/frissitKonyvByAzon/{azon}")
public ResponseEntity<Konyv> frissitKonyvByAzon(@PathVariable Long azon, @RequestBody Konyv ujKonyvData) {

    Optional<Konyv> regikonyvData=  konyvRepo.findById(azon);

    if (regikonyvData.isPresent()) {

       Konyv frissitKonyvData= regikonyvData.get();

       frissitKonyvData.setSzerzo(ujKonyvData.getSzerzo());
       frissitKonyvData.setCim(ujKonyvData.getCim());

       Konyv konyObj= konyvRepo.save(frissitKonyvData);

       return new ResponseEntity<>(konyObj, HttpStatus.OK);

    }

    return new ResponseEntity<>(HttpStatus.NOT_FOUND);

}
@DeleteMapping("/torolKonyvByAzon/{azon}")
    public ResponseEntity<HttpStatus> torolKonyvByAzon(@PathVariable Long azon) {
  konyvRepo.deleteById(azon);

  return new ResponseEntity<>(HttpStatus.OK);





}

}
