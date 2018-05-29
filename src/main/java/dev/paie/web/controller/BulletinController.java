package dev.paie.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Periode;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.BulletinSalaireRepository;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.RemunerationEmployeRepository;

@Controller
@RequestMapping("/bulletins")
public class BulletinController {

	@Autowired
	private PeriodeRepository periodeRepository;

	@Autowired
	private RemunerationEmployeRepository remunerationEmployeRepository;

	@Autowired
	private BulletinSalaireRepository bulletinSalaireRepository;

	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	public ModelAndView creerBulletin(Model model) {
		BulletinSalaire bulletin = new BulletinSalaire();
		model.addAttribute("bulletin", bulletin);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/creerBulletin");

		List<Periode> listePeriodes = periodeRepository.findAll();
		List<RemunerationEmploye> listeMatricules = remunerationEmployeRepository.findAll();

		mv.addObject("listePeriodes", listePeriodes);
		mv.addObject("listeMatricules", listeMatricules);

		return mv;
	}

}