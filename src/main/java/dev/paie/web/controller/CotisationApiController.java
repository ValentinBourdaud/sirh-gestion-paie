package dev.paie.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.paie.entite.Cotisation;
import dev.paie.repository.CotisationRepository;

@RestController
@RequestMapping("/api/cotisations")
public class CotisationApiController {

	@Autowired
	CotisationRepository cotisRepo;

	@RequestMapping(method = RequestMethod.GET)
	@Secured("ROLE_ADMINISTRATEUR")
	public List<Cotisation> trouverToutesLesCotisations() {
		return cotisRepo.findAll();
	}

	@RequestMapping(path = "/{code}", method = RequestMethod.GET)
	@Secured("ROLE_ADMINISTRATEUR")
	public ResponseEntity findCotisationById(@PathVariable String code) {
		Cotisation cot = cotisRepo.findByCode(code);
		if (cot == null) {
			Map<String, String> erreur = new HashMap<>();
			erreur.put("message : ", "Code de cotisation non trouve");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erreur);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(cotisRepo.findByCode(code));
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	@Secured("ROLE_ADMINISTRATEUR")
	public void insererCotisation(@RequestBody Cotisation cotisation) {
		this.cotisRepo.save(cotisation);
	}

	@RequestMapping(path = "/{code}", method = RequestMethod.PUT)
	@Secured("ROLE_ADMINISTRATEUR")
	public void modifCotisation(@PathVariable String code, @RequestBody Cotisation cotisation) {
		int id = cotisRepo.findByCode(code).getId();
		cotisation.setId(id);
		this.cotisRepo.save(cotisation);

	}

	@RequestMapping(path = "/{code}", method = RequestMethod.DELETE)
	@Secured("ROLE_ADMINISTRATEUR")
	public void supprimerCotisation(@PathVariable String code) {
		int id = cotisRepo.findByCode(code).getId();
		cotisRepo.delete(id);
	}

}
