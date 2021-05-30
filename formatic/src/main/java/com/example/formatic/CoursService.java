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
		}
		
		// liste de tous les cours
		public List<Cours> listAll() {
			 return repo.findAll();
		}
		// tous les cours d'un cursus
		
		public Cours AllCoursCursus(Cours cours) {
			return repo.AllCoursCursus(cours.getId_Cursus());
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
