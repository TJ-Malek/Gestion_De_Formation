package com.example.formatic;

//import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FormateurService {
	@Autowired
	private  FormateurRepository repo;
	/*@Autowired
	private  UtilisateurRepository repoUtilisateur;*/
	@Autowired
	private UtilisateurService serviceUser; 
	
	
	// Verifie si le formateur existe dans la BD
	
		public Formateur findFormateur(Formateur formateur) {
			/*Utilisateur user = new Utilisateur();
			user.setId(formateur.getId());
			Utilisateur u = serviceUser.get(user);*/
			return repo.findFormateur(formateur.getId());
			
		}
	// recupere etat formateur
	
		public Boolean getEtatFormateur(Formateur formateur) {
			return repo.getEtat(formateur.getId());
		}
	
	// change etat formateur
	
	public void setEtatFormateur(Formateur formateur) {
		
		Boolean etat = getEtatFormateur(formateur);
		System.out.println("etat = "+etat);
		Utilisateur user = new Utilisateur();
		user.setId(formateur.getId());
		Utilisateur u = serviceUser.get(user);
		if(u.getRole().equals("etudiant")) {
			u.setRole("formateur");
		} else {
			u.setRole("etudiant");
		}
		serviceUser.update(u);
		
		 repo.setEtat(!etat,formateur.getId());
	}
	
	// liste de tous les formateurs(sans infos utilisateur)
	public List<Formateur> listAll() {
		/* List<Formateur> listFormateur= new ArrayList<>();
		 for (Formateur l : repo.findAll()) {
			 listFormateur.add(get(l));
	      }
		 return listFormateur;*/
		return  repo.findAll();
	}
	
	// enregistre le formateur s'il n'existe pas dans la BD
	public Boolean save(Formateur formateur) {
		System.out.println("ok");
	
		if(findFormateur(formateur)==null) {
				repo.save(formateur);
				return true;
	}
		else {
				return false;
		}
	}
	
	// modification formateur(sans infos utilisateur)
	public void update(Formateur formateur) {
	/*	Utilisateur user = new Utilisateur();
		user.setId(formateur.getId());
		user.setEmail(formateur.getEmail());
		user.setMdp(formateur.getMdp());
		user.setNom(formateur.getNom());
		user.setPrenom(formateur.getPrenom());
		user.setRole(formateur.getRole());
		serviceUser.update(user);*/
		repo.save(formateur);
	}
	
	// recupere formateur par son id (sans infos utilisateur)
	public Formateur get(Formateur formateur) {
		/*Utilisateur user = new Utilisateur();
		user.setId(formateur.getId());*/
		
	//	Utilisateur u = serviceUser.get(user);
		System.out.println("*********** formateur idddddddddddddddddddddddddddddd = "+formateur.getId());	
	return repo.findById(formateur.getId()).get();
		//System.out.println("*********** formateur = "+f);
		/*f.setEmail(u.getEmail());
		f.setMdp(u.getMdp());
		f.setNom(u.getNom());
		f.setPrenom(u.getPrenom());
		f.setRole(u.getRole());*/
		//return f;
	}
	
	// supprime formateur
	public void delete(Formateur formateur) {
		repo.deleteById(formateur.getId());
		Utilisateur user = new Utilisateur();
		user.setId(formateur.getId());
		Utilisateur u = serviceUser.get(user);
		u.setRole("etudiant");
	}
}
