package dev.paie.service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.Cotisation;
import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.Periode;
import dev.paie.entite.ProfilRemuneration;
import dev.paie.entite.Utilisateur;
import dev.paie.entite.Utilisateur.ROLES;
import dev.paie.repository.CotisationRepository;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.ProfilRemunerationRepository;
import dev.paie.repository.UtilisateurRepository;

@Service
public class InitialiserDonneesServiceDev implements InitialiserDonneesService {

	@Autowired
	private GradeRepository gradeRepository;

	@Autowired
	private CotisationRepository cotisationRepository;

	@Autowired
	private EntrepriseRepository entrepriseRepository;

	@Autowired
	private PeriodeRepository periodeRepository;

	@Autowired
	private ProfilRemunerationRepository profilRemunerationRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@Override
	@Transactional
	public void initialiser() {
		listerUtilisateur();
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("init_config.xml");

		Collection<Entreprise> entreprises = ctx.getBeansOfType(Entreprise.class).values();
		for (Entreprise entreprise : entreprises) {
			entrepriseRepository.save(entreprise);
		}
		Collection<Grade> grades = ctx.getBeansOfType(Grade.class).values();
		for (Grade grade : grades) {
			gradeRepository.save(grade);
		}

		Collection<Cotisation> cotisations = ctx.getBeansOfType(Cotisation.class).values();
		for (Cotisation cotisation : cotisations) {
			cotisationRepository.save(cotisation);
		}

		Collection<ProfilRemuneration> profilRemunerations = ctx.getBeansOfType(ProfilRemuneration.class).values();
		for (ProfilRemuneration profilRemuneration : profilRemunerations) {
			profilRemunerationRepository.save(profilRemuneration);
		}

		for (int i = 0; i < 12; i++) {
			Periode p = new Periode();
			p.setDateDebut(LocalDate.of(LocalDate.now().getYear(), i + 1, 1));
			p.setDateFin(p.getDateDebut().with(TemporalAdjusters.lastDayOfMonth()));

			periodeRepository.save(p);

		}

		String iciUnMotDePasse = "topSecret";
		String iciMotDePasseHashe = this.passwordEncoder.encode(iciUnMotDePasse);

		ctx.close();

	}

	public void listerUtilisateur() {

		Utilisateur user1 = new Utilisateur();
		Utilisateur user2 = new Utilisateur();

		user1.setNomUtilisateur("robert");
		user1.setMotDePasse(passwordEncoder.encode("lalala"));
		user1.setEstActif(true);
		user1.setRole(ROLES.ROLE_ADMINISTRATEUR);

		utilisateurRepository.save(user1);

		user2.setNomUtilisateur("toto");
		user2.setMotDePasse(passwordEncoder.encode("lolilol"));
		user2.setEstActif(true);
		user2.setRole(ROLES.ROLE_UTILISATEUR);

		utilisateurRepository.save(user2);
	}

}
