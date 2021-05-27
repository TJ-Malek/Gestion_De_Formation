package persistence;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="Cours")
public class Cours {
	@Id
	Long id;
	Long id_Cursus;
	String designation;
	String image;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
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
	public Long getId_Cursus() {
		return id_Cursus;
	}
	public void setId_Cursus(Long id_Cursus) {
		this.id_Cursus = id_Cursus;
	}
	
	
}
