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
	// Verifie si l'formateur existe dans la BD
	
		public Formateur findFormateur(Formateur formateur) {
			/*ExampleMatcher FormateurMatcher = ExampleMatcher.matchingAny()
		                //.withIgnoreCase("nom", "prenom")
		                .withIgnorePaths("id","nom","prenom","role")
		                .withNullHandler(ExampleMatcher.NullHandler.INCLUDE)
		                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);*/
			
			return repo.findFormateur(formateur.getEmail(),formateur.getMdp());
			
		}
		
		// liste de tous les formateurs
		public List<Formateur> listAll() {
			return repo.findAll();
		}
		
		// enregistre l'formateur s'il n'existe pas dans la BD
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
