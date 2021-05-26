package persistence;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="Cursus")
public class Cursus {
	@Id
	Long id;
	String designation;
	String contenu;
	String pdf;
	String image;
	int volume_horaire;
	Boolean etat;
	String date_ajout;
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
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	public String getPdf() {
		return pdf;
	}
	public void setPdf(String pdf) {
		this.pdf = pdf;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getVolume_horaire() {
		return volume_horaire;
	}
	public void setVolume_horaire(int volume_horaire) {
		this.volume_horaire = volume_horaire;
	}
	public Boolean getEtat() {
		return etat;
	}
	public void setEtat(Boolean etat) {
		this.etat = etat;
	}
	public String getDate_ajout() {
		return date_ajout;
	}
	public void setDate_ajout(String date_ajout) {
		this.date_ajout = date_ajout;
	}
	
	
}
