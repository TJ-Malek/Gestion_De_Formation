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
	
	//services
	
	@Autowired
	private UtilisateurService serviceUser; 
	@Autowired
	private FormateurService serviceFormateur; 
	
	//mapping
	
	//visiteur
	@RequestMapping("/")
	public String viewHomePage() {
	return "index";
	}
	@RequestMapping("/deconnexion")
	public String deconnexion() {
	return "index";
	}
	@RequestMapping("/inscription")
	public String inscription() {

		return "inscription";
	}
	//utilisateur
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") Utilisateur user) {
		user.setRole("etudiant");
		serviceUser.save(user);
		
		return "redirect:/";
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
			if(find.getRole().equals("etudiant")) {
		return "redirect:/profil/"+find.getId();
	} else {
		return "/admin";
	}
		}
		else {
			return "redirect:/";
		}
	}
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public String updateUser(@ModelAttribute("user") Utilisateur user) {
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
	//formateur
	@RequestMapping("/demandeFormateur/{id}")
	public String demandeFormateur(@PathVariable(name = "id") Long id) {

		return "demandeFormateur";
	}
	@RequestMapping(value = "/saveFormateur/{id}", method = RequestMethod.POST)
	public String saveFormateur(@ModelAttribute("formateur") Formateur formateur,@PathVariable(name = "id") Long id) {
		formateur.setEtat(false);
		formateur.setId(id);
		Boolean test = serviceFormateur.save(formateur);
		System.out.println("formateur = "+test);
		return "redirect:/profil/"+formateur.getId();
	}
}
