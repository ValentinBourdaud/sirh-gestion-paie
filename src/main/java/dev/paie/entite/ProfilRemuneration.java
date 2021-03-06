package dev.paie.entite;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "profilRemuneration")
public class ProfilRemuneration {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String code;

	@ManyToMany
	@JoinTable(name = "profil_cotisations_non_imposables", joinColumns = @JoinColumn(name = "profilRemuneration_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "cotisation_non_imposable_id", referencedColumnName = "id"))
	@Column(name = "cotisation_non_imposable")
	private List<Cotisation> cotisationsNonImposables;

	@ManyToMany
	@JoinTable(name = "profil_cotisations_imposables", joinColumns = @JoinColumn(name = "profilRemuneration_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "cotisation_imposable_id", referencedColumnName = "id"))
	@Column(name = "cotisation_non_imposable")
	private List<Cotisation> cotisationsImposables;

	@ManyToMany
	@JoinTable(name = "profil_avantage", joinColumns = @JoinColumn(name = "profilRemuneration_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "avantage_id", referencedColumnName = "id"))
	private List<Avantage> avantages;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Cotisation> getCotisationsNonImposables() {
		return cotisationsNonImposables;
	}

	public void setCotisationsNonImposables(List<Cotisation> cotisationsNonImposables) {
		this.cotisationsNonImposables = cotisationsNonImposables;
	}

	public List<Cotisation> getCotisationsImposables() {
		return cotisationsImposables;
	}

	public void setCotisationsImposables(List<Cotisation> cotisationsImposables) {
		this.cotisationsImposables = cotisationsImposables;
	}

	public List<Avantage> getAvantages() {
		return avantages;
	}

	public void setAvantages(List<Avantage> avantages) {
		this.avantages = avantages;
	}

	public String toString() {
		return this.code;
	}

}
