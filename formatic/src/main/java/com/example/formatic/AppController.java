package com.example.formatic;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	@Autowired
	private CursusService serviceCursus; 
	@Autowired
	private CoursService serviceCours; 
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
		return "redirect:/admin";
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
	//admin
	@RequestMapping("/admin")
	public String viewAdmin(Model model) {
		List<Utilisateur> listUsers = serviceUser.listAll();
		model.addAttribute("listUsers", listUsers);
	return "admin";
	}
	@RequestMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable(name = "id") Long id) {
		Utilisateur user = new Utilisateur();
		user.setId(id);
		serviceUser.delete(user);
		return "redirect:/admin";		
	}
	@RequestMapping("/formateurs")
	public String viewFormateurs(Model model) {
		List<Formateur> listFormateurs= serviceFormateur.listAll();
		model.addAttribute("listFormateurs", listFormateurs);
	return "formateurs";
	}
	@RequestMapping("/deleteFormateur/{id}")
	public String deleteFormateur(@PathVariable(name = "id") Long id) {
		Formateur formateur = new Formateur();
		formateur.setId(id);
		serviceFormateur.delete(formateur);
		return "redirect:/formateurs";		
	}
	@RequestMapping(value = "/updateFormateur", method = RequestMethod.POST)
	public String updateUser(@ModelAttribute("formateur") Formateur formateur) {
		Formateur f = serviceFormateur.get(formateur);
		formateur.setEtat(f.getEtat());
		serviceFormateur.update(formateur);
		
		return "redirect:/formateurs";
	}
	@RequestMapping("/etatFormateur/{id}")
	public String etatFormateur(@PathVariable(name = "id") Long id) {
		Formateur formateur = new Formateur();
		formateur.setId(id);
		System.out.println("formateur id etat = "+formateur.getId());
		serviceFormateur.setEtatFormateur(formateur);
		return "redirect:/formateurs";		
	}
	//cursus formateur
	@RequestMapping("/cursusFormateur/{id}")
	public String cursusFormateur(@PathVariable(name = "id") Long id,Model model) {
		Cursus cursus = new Cursus();
		cursus.setId_Formateur(id);
		List<Cursus> listCursusFormateur= serviceCursus.AllCursusFormateur(cursus);
		model.addAttribute("listCursusFormateur", listCursusFormateur);
		
		return "cursusFormateur";		
	}
	@RequestMapping("/ajoutCursus/{id}")
	public String ajoutCursus(@PathVariable(name = "id") Long id) {
		/*Cursus cursus = new Cursus();
		cursus.setId_Formateur(id);*/
		
		
		return "ajoutCursus";		
	}
	@RequestMapping(value = "/saveCursus/{id}", method = RequestMethod.POST)
	public String saveCursus(@ModelAttribute("cursus") Cursus cursus,@PathVariable(name = "id") Long id) {
		cursus.setEtat(false);
		cursus.setId_Formateur(id);
		Date date = new Date(); 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(formatter.format(date));
		cursus.setDate_ajout(formatter.format(date));
		Boolean test = serviceCursus.save(cursus);
		
		return "redirect:/cursusFormateur/"+cursus.getId_Formateur();
	}
	@RequestMapping("/CursusDetails/{id}/{idCursus}")
	public String detailsCursus(@PathVariable(name = "id") Long id,@PathVariable(name = "idCursus") Long idCursus,Model model) {
		Cursus cu = new Cursus();
		cu.setId(idCursus);
		Cursus cursus = serviceCursus.get(cu);
		model.addAttribute("cursus", cursus);
		
		return "CursusDetails";		
	}
	@RequestMapping(value = "/updateCursus", method = RequestMethod.POST)
	public String updateCursus(@ModelAttribute("cursus") Cursus cursus) {
		Cursus c = serviceCursus.get(cursus);
		cursus.setEtat(c.getEtat());
		cursus.setDate_ajout(c.getDate_ajout());
		cursus.setId_Formateur(c.getId_Formateur());
		serviceCursus.update(cursus);
		
		return "redirect:/CursusDetails/"+cursus.getId_Formateur()+"/"+cursus.getId();
	}
	@RequestMapping("/deleteCursus/{id}/{idCursus}")
	public String deleteCursus(@PathVariable(name = "id") Long id,@PathVariable(name = "idCursus") Long idCursus) {
		Cursus cursus = new Cursus();
		cursus.setId(idCursus);
		serviceCursus.delete(cursus);
		return "redirect:/cursusFormateur/"+id;
	}
	//cours formateur
	@RequestMapping("/coursFormateur/{id}/{idCursus}")
	public String coursFormateur(@PathVariable(name = "id") Long id,@PathVariable(name = "idCursus") Long idCursus,Model model) {
		Cours cours = new Cours();
		cours.setId_Cursus(idCursus);
		List<Cours> listCoursFormateur= serviceCours.AllCoursCursus(cours);
		model.addAttribute("listCoursFormateur", listCoursFormateur);
		
		return "coursFormateur";		
	}
	@RequestMapping("/ajoutCours/{id}/{idCursus}")
	public String ajoutCours(@PathVariable(name = "id") Long id,@PathVariable(name = "idCursus") Long idCursus) {
		/*Cours cours = new Cours();
		cours.setId_Formateur(id);*/
		
		
		return "ajoutCours";		
	}
	@RequestMapping(value = "/saveCours/{id}/{idCursus}", method = RequestMethod.POST)
	public String saveCours(@ModelAttribute("cours") Cours cours,@PathVariable(name = "id") Long id,@PathVariable(name = "idCursus") Long idCursus) {
		cours.setEtat(false);
		cours.setId_Cursus(idCursus);
		Date date = new Date(); 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(formatter.format(date));
		cours.setDate_ajout(formatter.format(date));
		Boolean test = serviceCours.save(cours);
		
		return "redirect:/coursFormateur/"+id+'/'+cours.getId_Cursus();
	}
	@RequestMapping("/CoursDetails/{id}/{idCursus}/{idCours}")
	public String detailsCours(@PathVariable(name = "id") Long id,@PathVariable(name = "idCours") Long idCours,@PathVariable(name = "idCursus") Long idCursus,Model model) {
		Cours cu = new Cours();
		cu.setId(idCours);
		Cours cours = serviceCours.get(cu);
		model.addAttribute("cours", cours);
		
		return "CoursDetails";		
	}
	@RequestMapping(value = "/updateCours", method = RequestMethod.POST)
	public String updateCours(@ModelAttribute("cours") Cours cours) {
		Cours c = serviceCours.get(cours);
		cours.setEtat(c.getEtat());
		cours.setDate_ajout(c.getDate_ajout());
		cours.setId_Cursus(c.getId_Cursus());
		Cursus cu = new Cursus();
		cu.setId(cours.getId_Cursus());
		Cursus cur = serviceCursus.get(cu);
		serviceCours.update(cours);
		
		return "redirect:/CoursDetails/"+cur.getId_Formateur()+'/'+cours.getId_Cursus()+"/"+cours.getId();
	}
	@RequestMapping("/deleteCours/{id}/{idCursus}/{idCours}")
	public String deleteCours(@PathVariable(name = "id") Long id,@PathVariable(name = "idCours") Long idCours,@PathVariable(name = "idCursus") Long idCursus) {
		Cours cours = new Cours();
		cours.setId(idCours);
		serviceCours.delete(cours);
		return "redirect:/coursFormateur/"+id+"/"+idCursus;
	}
}
