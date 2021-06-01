package com.example.formatic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ChapitreService {
	@Autowired
	private  ChapitreRepository repo;
	@Autowired
	private  Cursus_SuivisService serviceCursus_Suivis;
	@Autowired
	private  CoursService serviceCours;
		// Verifie si le chapitre existe dans la BD
	
			public Chapitre FindChapitre(Chapitre chapitre) {
				return repo.FindChapitre(chapitre.getDesignation(),chapitre.getId_Cours());
			}
		// recupere etat chapitre
		
			public Boolean getEtatChapitre(Chapitre chapitre) {
				return repo.getEtat(chapitre.getId());
			}
		
		// change etat chapitre
		
		public void setEtatChapitre(Chapitre chapitre) {
			Boolean etat = getEtatChapitre(chapitre);
		 repo.setEtat(!etat,chapitre.getId());
		
		 Boolean etatActif= !etat;
		 Chapitre ch = new Chapitre();
		 ch.setId(chapitre.getId());
		 Cours cours= new Cours();
		 cours.setId(get(ch).getId_Cours());
		 
		 if(etatActif==true) {
			 
			 serviceCursus_Suivis.updateAllCursus_SuivisChapitre(chapitre.getId(),serviceCours.get(cours).getId_Cursus());
		 } else {
			 System.out.println("etat null change");
			 serviceCursus_Suivis.updateAllCursus_SuivisChapitre(null,serviceCours.get(cours).getId_Cursus()); 
		 }
		}
		
		// liste de tous les chapitre
		public List<Chapitre> listAll() {
			 return repo.findAll();
		}
		// tous les chapitres d'un cours
		
		public List<Chapitre> AllChapitreCours(Chapitre chapitre) {
			return repo.AllChapitreCours(chapitre.getId_Cours());
		}
		
		// tous les chapitres actifs d'un cours
		
		public List<Chapitre> AllChapitreActifCours(Chapitre chapitre) {
			return repo.AllChapitreActifCours(chapitre.getId_Cours());
		}
		
	// premier chapitre actif d'un cours
		
		public Chapitre FirstChapitreCours(Cours cours) {
			return repo.FirstChapitreCours(cours.getId());
		}
		// enregistre le chapitre s'il n'existe pas dans la BD
		public Boolean save(Chapitre chapitre) {
			if(FindChapitre(chapitre)==null) {
					repo.save(chapitre);
					return true;
		}
			else {
					return false;
			}
		}
		
		// modification chapitre
		public void update(Chapitre chapitre) {
			repo.save(chapitre);
		}
		
		// recupere chapitre par son id 
		public Chapitre get(Chapitre chapitre) {	
		    return repo.findById(chapitre.getId()).get();	
		}
		
		// supprime chapitre
		public void delete(Chapitre chapitre) {
			repo.deleteById(chapitre.getId());
		}
		// recupere le chapitre suivant
		
		public Chapitre ClosestChapitreCours(Chapitre chapitre) {
			return repo.ClosestChapitreCours(chapitre.getId_Cours(),chapitre.getDate_ajout());
		}
		
}
