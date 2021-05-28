package com.example.formatic;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="Chapitre")
public class Chapitre {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	Long id_Cours;
	String designation;
	String date_ajout;
	Boolean etat;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDate_ajout() {
		return date_ajout;
	}
	public void setDate_ajout(String date_ajout) {
		this.date_ajout = date_ajout;
	}
	public Boolean getEtat() {
		return etat;
	}
	public void setEtat(Boolean etat) {
		this.etat = etat;
	}
	public Long getId_Cours() {
		return id_Cours;
	}
	public void setId_Cours(Long id_Cours) {
		this.id_Cours = id_Cours;
	}
	
	
}
