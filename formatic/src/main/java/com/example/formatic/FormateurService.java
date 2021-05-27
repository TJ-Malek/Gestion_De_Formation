package com.example.formatic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FormateurService {
	@Autowired
	private  FormateurRepository repo;
	@Autowired
	private  UtilisateurRepository repoUtilisateur;
	@Autowired
	private UtilisateurService serviceUser; 
	
	
	// Verifie si le formateur existe dans la BD
	
		public Utilisateur findFormateur(Formateur formateur) {
			Utilisateur user = new Utilisateur();
			user.setEmail(formateur.getEmail());
			//user.setMdp(formateur.getMdp());
			serviceUser.findUtilisateur(null);
			
			return repoUtilisateur.findUtilisateurByEmail(user.getEmail());
			
		}
		
		// liste de tous les formateurs
		public List<Formateur> listAll() {
			return repo.findAll();
		}
		
		// enregistre le formateur s'il n'existe pas dans la BD
		public Boolean save(Formateur formateur) {
			if(findFormateur(formateur)==null) {
					repo.save(formateur);
					return true;
		}
			else {
					return false;
			}
		}
		
		// modification formateur
		public void update(Formateur formateur) {
			repo.save(formateur);
		}
		
		// recupere formateur par son id 
		public Formateur get(Formateur formateur) {
			return repo.findById(formateur.getId()).get();
		}
		
		// supprime formateur
		public void delete(Formateur formateur) {
			repo.deleteById(formateur.getId());
		}
}
