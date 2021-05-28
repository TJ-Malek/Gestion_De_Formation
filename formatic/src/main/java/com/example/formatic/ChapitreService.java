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
		// Verifie si le chapitre existe dans la BD
	
			public Chapitre FindChapitre(Chapitre chapitre) {
				return repo.FindChapitre(chapitre.getDesignation(),chapitre.getId_Cours());
			}
		// recupere etat chapitre
		
			public Boolean getEtatChapitre(Chapitre chapitre) {
				return repo.getEtat(chapitre.getId()).getEtat();
			}
		
		// change etat chapitre
		
		public Chapitre setEtatChapitre(Chapitre chapitre) {
			Boolean etat = getEtatChapitre(chapitre);
			return repo.setEtat(!etat,chapitre.getId());
		}
		
		// liste de tous les chapitre
		public List<Chapitre> listAll() {
			 return repo.findAll();
		}
		// tous les chapitre d'un cursus
		
		public Chapitre AllChapitreCursus(Chapitre chapitre) {
			return repo.AllChapitreCours(chapitre.getId_Cours());
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
}
