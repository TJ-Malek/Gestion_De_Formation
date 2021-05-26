package persistence;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="Cursus_Suivis")
public class Cursus_Suivis {
	@Id
	Long id;
	Long id_Utilisateur;
	Long id_Chapitre;
	Long id_Cours;
	Long id_Cursus;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getId_Utilisateur() {
		return id_Utilisateur;
	}
	public void setId_Utilisateur(Long id_Utilisateur) {
		this.id_Utilisateur = id_Utilisateur;
	}
	public Long getId_Chapitre() {
		return id_Chapitre;
	}
	public void setId_Chapitre(Long id_Chapitre) {
		this.id_Chapitre = id_Chapitre;
	}
	public Long getId_Cours() {
		return id_Cours;
	}
	public void setId_Cours(Long id_Cours) {
		this.id_Cours = id_Cours;
	}
	public Long getId_Cursus() {
		return id_Cursus;
	}
	public void setId_Cursus(Long id_Cursus) {
		this.id_Cursus = id_Cursus;
	}
	
	
}
