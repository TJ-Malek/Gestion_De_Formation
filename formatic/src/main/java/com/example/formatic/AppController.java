package com.example.formatic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//import persistence.Utilisateur;


@Controller
public class AppController {
	@Autowired
	private UtilisateurService serviceUser; 
	@RequestMapping("/")
	public String viewHomePage() {
	Utilisateur utilisateur = new Utilisateur();
	utilisateur.setEmail("a@a.com");
	utilisateur.setMdp("123");
	utilisateur.setNom("aNom");
	utilisateur.setPrenom("aPrenom");
	utilisateur.setRole("etudiant");
	//utilisateur.setId((long) 1);
	
	Boolean t = serviceUser.save(utilisateur);
	System.out.println("saved = "+t);
	return "index";
	}
	@RequestMapping("/profil")
	public String viewProfilPage() {
		return "profil";
	}
	
}
