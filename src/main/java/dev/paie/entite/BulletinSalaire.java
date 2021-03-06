package dev.paie.entite;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bulletin")
public class BulletinSalaire {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne
	private RemunerationEmploye remunerationEmploye;

	@ManyToOne
	private Periode periode;

	@Column
	private BigDecimal primeExceptionnelle;

	@Column
	private ZonedDateTime dateCreation;

	public RemunerationEmploye getRemunerationEmploye() {
		return remunerationEmploye;
	}

	public void setRemunerationEmploye(RemunerationEmploye remunerationEmploye) {
		this.remunerationEmploye = remunerationEmploye;
	}

	public Periode getPeriode() {
		return periode;
	}

	public void setPeriode(Periode periode) {
		this.periode = periode;
	}

	public BigDecimal getPrimeExceptionnelle() {
		return primeExceptionnelle;
	}

	public void setPrimeExceptionnelle(BigDecimal primeExceptionnelle) {
		this.primeExceptionnelle = primeExceptionnelle;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ZonedDateTime getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(ZonedDateTime dateCreation) {
		this.dateCreation = dateCreation;
	}

}
