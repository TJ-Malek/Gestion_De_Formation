package com.example.formatic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;





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
	@RequestMapping("/profil/{id}")
	public ModelAndView viewProfilPage(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("profil");
		Utilisateur user = new Utilisateur();
		user.setId(id);
	    Utilisateur userConnected = serviceUser.get(user);
		mav.addObject("user", userConnected);
		
		return mav;
	}
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public String auth(@ModelAttribute("user") Utilisateur user) {
		Utilisateur find = serviceUser.findUtilisateurByEmailAndMdp(user);
		if(find!=null) {
			
		return "redirect:/profil/"+find.getId();
	}
		else {
			return "redirect:/";
		}
	}
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") Utilisateur user) {
		Utilisateur u = new Utilisateur();
		u.setId(user.getId());
		Utilisateur u2= serviceUser.get(u);
		u2.setEmail(user.getEmail());
		u2.setNom(user.getNom());
		u2.setPrenom(user.getPrenom());
		u2.setMdp(user.getMdp());
		serviceUser.update(u2);
		
		return "redirect:/profil/"+user.getId();
	}
}
