package com.example.formatic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CoursService {
	@Autowired
	private  CoursRepository repo;
	@Autowired
	private  Cursus_SuivisService serviceCursus_Suivis;
		// Verifie si le cours existe dans la BD
	
			public Cours FindCours(Cours cours) {
				return repo.FindCours(cours.getDesignation(),cours.getId_Cursus());
			}
		// recupere etat cours
		
			public Boolean getEtatCours(Cours cours) {
				return repo.getEtat(cours.getId());
			}
		
		// change etat cours
		
		public void setEtatCours(Cours cours) {
			Boolean etat = getEtatCours(cours);
			 repo.setEtat(!etat,cours.getId());
			 Boolean etatActif= !etat;
			 System.out.println("etat= "+etatActif);
			 if(etatActif==true) {
				 Cours c = new Cours();
				 c.setId(cours.getId());
				
				 serviceCursus_Suivis.updateAllCursus_SuivisCours(cours.getId(), get(c).getId_Cursus());
			 }
		}
		
		// liste de tous les cours
		public List<Cours> listAll() {
			 return repo.findAll();
		}
		// tous les cours d'un cursus
		
		public List<Cours> AllCoursCursus(Cours cours) {
			return repo.AllCoursCursus(cours.getId_Cursus());
		}
		
		// tous les cours actifs d'un cursus
		
		public List<Cours> AllCoursActifCursus(Cours cours) {
			return repo.AllCoursActifCursus(cours.getId_Cursus());
		}
		
		// premier cours actif d'un cursus
		
		public Cours FirstCoursCursus(Cursus cursus) {
			return repo.FirstCoursCursus(cursus.getId());
		}
		
		// enregistre le cours s'il n'existe pas dans la BD
		public Boolean save(Cours cours) {
			if(FindCours(cours)==null) {
					repo.save(cours);
					return true;
		}
			else {
					return false;
			}
		}
		
		// modification cours
		public void update(Cours cours) {
			repo.save(cours);
		}
		
		// recupere cours par son id 
		public Cours get(Cours cours) {	
		    return repo.findById(cours.getId()).get();	
		}
		
		// supprime cours
		public void delete(Cours cours) {
			repo.deleteById(cours.getId());
		}
}
