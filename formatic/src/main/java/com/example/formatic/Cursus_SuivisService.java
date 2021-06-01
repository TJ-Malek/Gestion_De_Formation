package com.example.formatic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class Cursus_SuivisService {
	@Autowired
	private  Cursus_SuivisRepository repo;
		// Verifie si le cursus_Suivis existe dans la BD
	
			public Cursus_Suivis FindCursus_Suivis(Cursus_Suivis cursus_Suivis) {
				return repo.FindCursus_Suivis(cursus_Suivis.getId_Utilisateur(),cursus_Suivis.getId_Cursus());
			}

		
		// liste de tous les cursus_Suivis
		public List<Cursus_Suivis> listAll() {
			 return repo.findAll();
		}
		// toutes les cursus_Suiviss d'un etudiant
		
		public List<Cursus_Suivis> AllCursus_SuivisCursus(Cursus_Suivis cursus_Suivis) {
			return repo.AllCursus_Suivis(cursus_Suivis.getId_Utilisateur());
		}
		
		// enregistre le cursus_Suivis s'il n'existe pas dans la BD
		public Boolean save(Cursus_Suivis cursus_Suivis) {
			if(FindCursus_Suivis(cursus_Suivis)==null) {
					repo.save(cursus_Suivis);
					return true;
		}
			else {
					return false;
			}
		}
		
		// modification cursus_Suivis
		public void update(Cursus_Suivis cursus_Suivis) {
			repo.save(cursus_Suivis);
		}
		
		// recupere cursus_Suivis par son id (sans chapitre ni section)
		public Cursus_Suivis get(Cursus_Suivis cursus_Suivis) {	
		    return repo.findById(cursus_Suivis.getId()).get();	
		}
		
		//update cours apre etat cours=1
		public void updateAllCursus_SuivisCours(Long id_Cours,Long id_Cursus ){	
		  repo.updateAllCursus_SuivisCours(id_Cours, id_Cursus);	
		}
		//update cours apre etat cours=1
				public void updateAllCursus_SuivisChapitre(Long id_Chapitre,Long id_Cursus ){	
				  repo.updateAllCursus_SuivisChapitre(id_Chapitre, id_Cursus);	
				}
		// supprime cursus_Suivis
		public void delete(Cursus_Suivis cursus_Suivis) {
			repo.deleteById(cursus_Suivis.getId());
		}
}
