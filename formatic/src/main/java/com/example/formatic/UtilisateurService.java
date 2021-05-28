package com.example.formatic;

import java.util.List;
//import java.util.Optional;

//import org.springframework.data.domain.Example;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import persistence.Utilisateur;

@Service
@Transactional
public class UtilisateurService {
	@Autowired
	private  UtilisateurRepository repo;
	
	// authentification
	
	public Utilisateur findUtilisateurByEmailAndMdp(Utilisateur utilisateur) {
		return repo.findUtilisateurByEmailAndMdp(utilisateur.getEmail(),utilisateur.getMdp());	
	}
	
	// Verifie si l'utilisateur existe dans la BD
	
	public Utilisateur findUtilisateurByEmail(Utilisateur utilisateur) {
		/*ExampleMatcher UtilisateurMatcher = ExampleMatcher.matchingAny()
	                //.withIgnoreCase("nom", "prenom")
	                .withIgnorePaths("id","nom","prenom","role")
	                .withNullHandler(ExampleMatcher.NullHandler.INCLUDE)
	                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);*/
		Utilisateur u = repo.findUtilisateur(utilisateur.getEmail());
		System.out.println("email returned in find by  email = "+utilisateur.getEmail());
		return u;
		
	}
	
	// liste de tous les utilisateurs
	public List<Utilisateur> listAll() {
		return repo.findAll();
	}
	
	// enregistre l'utilisateur s'il n'existe pas dans la BD
	public Boolean save(Utilisateur utilisateur) {
		System.out.println("email right here = "+utilisateur.getEmail());
		if(findUtilisateurByEmail(utilisateur)==null) {
			System.out.println("email here = "+utilisateur.getEmail());
				repo.save(utilisateur);
				return true;
	}
		else {
				return false;
		}
	}
	
	// modification utilisateur
	public void update(Utilisateur utilisateur) {
		repo.save(utilisateur);
	}
	
	// recupere utilisateur par son id 
	public Utilisateur get(Utilisateur utilisateur) {
		return repo.findById(utilisateur.getId()).get();
	}
	
	// supprime utilisateur
	public void delete(Utilisateur utilisateur) {
		repo.deleteById(utilisateur.getId());
	}
}
