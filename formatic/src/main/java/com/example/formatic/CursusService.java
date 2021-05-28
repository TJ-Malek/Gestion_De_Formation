package com.example.formatic;

//import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CursusService {
	@Autowired
	private  CursusRepository repo;
	/*@Autowired
	private  FormateurRepository repoFormateur;
	@Autowired
	private FormateurService serviceFormateur; */
	
	
	// Verifie si le cursus existe dans la BD
	
		public Cursus FindCursus(Cursus cursus) {
			return repo.FindCursus(cursus.getDesignation(),cursus.getId_Formateur());
		}
	// tous les cursus d'un formateur
	
	public Cursus AllCursusFormateur(Cursus cursus) {
		return repo.AllCursusFormateur(cursus.getId_Formateur());
	}
	// recupere etat cursus
	
		public Boolean getEtatCursus(Cursus cursus) {
			return repo.getEtat(cursus.getId()).getEtat();
		}
	
	// change etat cursus
	
	public Cursus setEtatCursus(Cursus cursus) {
		Boolean etat = getEtatCursus(cursus);
		return repo.setEtat(!etat,cursus.getId());
	}
	
	// liste de tous les cursuss
	public List<Cursus> listAll() {
		 return repo.findAll();
	}
	
	// enregistre le cursus s'il n'existe pas dans la BD
	public Boolean save(Cursus cursus) {
		if(FindCursus(cursus)==null) {
				repo.save(cursus);
				return true;
	}
		else {
				return false;
		}
	}
	
	// modification cursus
	public void update(Cursus cursus) {
		repo.save(cursus);
	}
	
	// recupere cursus par son id (sans formateur)
	public Cursus get(Cursus cursus) {
		/*Formateur formateur = new Formateur();
		formateur.setId(cursus.getId_Formateur());
		
		Formateur f = serviceFormateur.get(formateur);*/
		
	    return repo.findById(cursus.getId()).get();
		
	}
	
	// supprime cursus
	public void delete(Cursus cursus) {
		repo.deleteById(cursus.getId());
	}
}
