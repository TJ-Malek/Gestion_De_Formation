package com.example.formatic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SectionService {
	@Autowired
	private  SectionRepository repo;
		// Verifie si le section existe dans la BD
	
			public Section FindSection(Section section) {
				return repo.FindSection(section.getDesignation(),section.getId_Chapitre());
			}
		// recupere etat section
		
			public Boolean getEtatSection(Section section) {
				return repo.getEtat(section.getId()).getEtat();
			}
		
		// change etat section
		
		public Section setEtatSection(Section section) {
			Boolean etat = getEtatSection(section);
			return repo.setEtat(!etat,section.getId());
		}
		
		// liste de tous les section
		public List<Section> listAll() {
			 return repo.findAll();
		}
		// toutes les sections d'un chapitre
		
		public Section AllSectionCursus(Section section) {
			return repo.AllSectionChapitre(section.getId_Chapitre());
		}
		
		// enregistre le section s'il n'existe pas dans la BD
		public Boolean save(Section section) {
			if(FindSection(section)==null) {
					repo.save(section);
					return true;
		}
			else {
					return false;
			}
		}
		
		// modification section
		public void update(Section section) {
			repo.save(section);
		}
		
		// recupere section par son id 
		public Section get(Section section) {	
		    return repo.findById(section.getId()).get();	
		}
		
		// supprime section
		public void delete(Section section) {
			repo.deleteById(section.getId());
		}
}
