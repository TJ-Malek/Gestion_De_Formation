package com.example.formatic;

import javax.persistence.Entity;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToOne;

@Entity(name="Formateur")
public class Formateur {
	/*@OneToOne(optional = false)
	@JoinColumn(name = "id", referencedColumnName = "id")
	private Utilisateur utilisateur;*/
	@Id
	Long id;
	int telephone;
	String parcours;
	Boolean etat;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getTelephone() {
		return telephone;
	}
	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}
	public String getParcours() {
		return parcours;
	}
	public void setParcours(String parcours) {
		this.parcours = parcours;
	}
	public Boolean getEtat() {
		return etat;
	}
	public void setEtat(Boolean etat) {
		this.etat = etat;
	}
	
	
}
