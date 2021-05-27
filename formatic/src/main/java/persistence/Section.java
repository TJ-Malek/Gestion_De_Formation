package persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="Section")
public class Section {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	Long id_Chapitre;
	String designation;
	Boolean etat;
	String image;
	String titre;
	String contenu;
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
	public Boolean getEtat() {
		return etat;
	}
	public void setEtat(Boolean etat) {
		this.etat = etat;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	public String getDate_ajout() {
		return date_ajout;
	}
	public void setDate_ajout(String date_ajout) {
		this.date_ajout = date_ajout;
	}
	public Long getId_Chapitre() {
		return id_Chapitre;
	}
	public void setId_Chapitre(Long id_Chapitre) {
		this.id_Chapitre = id_Chapitre;
	}
    
    
}
