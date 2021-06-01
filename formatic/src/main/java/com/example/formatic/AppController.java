package com.example.formatic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
	@Autowired
	private ChapitreService serviceChapitre; 
	@Autowired
	private SectionService serviceSection; 
	@Autowired
	private Cursus_SuivisService serviceCursus_Suivis; 
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
	@RequestMapping("/etatCursus/{id}/{idCursus}")
	public String etatCursus(@PathVariable(name = "id") Long id,@PathVariable(name = "idCursus") Long idCursus) {
		Cursus cursus = new Cursus();
		cursus.setId(idCursus);
		serviceCursus.setEtatCursus(cursus);
		return "redirect:/CursusDetails/"+id+"/"+cursus.getId();
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
	@RequestMapping("/etatCours/{id}/{idCursus}/{idCours}")
	public String etatCours(@PathVariable(name = "id") Long id,@PathVariable(name = "idCursus") Long idCursus,@PathVariable(name = "idCours") Long idCours) {
		Cours cours= new Cours();
		cours.setId(idCours);
		serviceCours.setEtatCours(cours);
		return "redirect:/CoursDetails/"+id+"/"+idCursus+"/"+cours.getId();
	}
	
	//chapitre formateur
		@RequestMapping("/chapitreFormateur/{id}/{idCursus}/{idCours}")
		public String chapitreFormateur(@PathVariable(name = "id") Long id,@PathVariable(name = "idCursus") Long idCursus,@PathVariable(name = "idCours") Long idCours,Model model) {
			Chapitre chapitre = new Chapitre();
			chapitre.setId_Cours(idCours);
			List<Chapitre> listChapitreFormateur= serviceChapitre.AllChapitreCours(chapitre);
			model.addAttribute("listChapitreFormateur", listChapitreFormateur);
			
			return "chapitreFormateur";		
		}
		@RequestMapping("/ajoutChapitre/{id}/{idCursus}/{idCours}")
		public String ajoutChapitre(@PathVariable(name = "id") Long id,@PathVariable(name = "idCursus") Long idCursus,@PathVariable(name = "idCours") Long idCours) {
			/*Chapitre chapitre = new Chapitre();
			chapitre.setId_Formateur(id);*/
			
			
			return "ajoutChapitre";		
		}
		@RequestMapping(value = "/saveChapitre/{id}/{idCursus}/{idCours}", method = RequestMethod.POST)
		public String saveChapitre(@ModelAttribute("chapitre") Chapitre chapitre,@PathVariable(name = "id") Long id,@PathVariable(name = "idCursus") Long idCursus,@PathVariable(name = "idCours") Long idCours) {
			chapitre.setEtat(false);
			chapitre.setId_Cours(idCours);
			Date date = new Date(); 
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println(formatter.format(date));
			chapitre.setDate_ajout(formatter.format(date));
			Boolean test = serviceChapitre.save(chapitre);
			
			return "redirect:/chapitreFormateur/"+id+'/'+idCursus+'/'+chapitre.getId_Cours();
		}
		@RequestMapping("/ChapitreDetails/{id}/{idCursus}/{idCours}/{idChapitre}")
		public String detailsChapitre(@PathVariable(name = "id") Long id,@PathVariable(name = "idChapitre") Long idChapitre,@PathVariable(name = "idCursus") Long idCursus,@PathVariable(name = "idCours") Long idCours,Model model) {
			Chapitre cu = new Chapitre();
			cu.setId(idChapitre);
			Chapitre chapitre = serviceChapitre.get(cu);
			model.addAttribute("chapitre", chapitre);
			
			return "ChapitreDetails";		
		}
		@RequestMapping(value = "/updateChapitre", method = RequestMethod.POST)
		public String updateChapitre(@ModelAttribute("chapitre") Chapitre chapitre) {
			Chapitre c = serviceChapitre.get(chapitre);
			chapitre.setEtat(c.getEtat());
			chapitre.setDate_ajout(c.getDate_ajout());
			chapitre.setId_Cours(c.getId_Cours());
			Cours cu = new Cours();
			cu.setId(chapitre.getId_Cours());
			Cours cur = serviceCours.get(cu);
			Cursus curs = new Cursus();
			curs.setId(cur.getId_Cursus());
			Long formateurId=serviceCursus.get(curs).getId_Formateur();
			serviceChapitre.update(chapitre);
			
			return "redirect:/ChapitreDetails/"+formateurId+'/'+cur.getId_Cursus()+'/'+chapitre.getId_Cours()+"/"+chapitre.getId();
		}
		@RequestMapping("/deleteChapitre/{id}/{idCursus}/{idCours}/{idChapitre}")
		public String deleteChapitre(@PathVariable(name = "id") Long id,@PathVariable(name = "idCursus") Long idCursus,@PathVariable(name = "idChapitre") Long idChapitre,@PathVariable(name = "idCours") Long idCours) {
			Chapitre chapitre = new Chapitre();
			chapitre.setId(idChapitre);
			serviceChapitre.delete(chapitre);
			return "redirect:/chapitreFormateur/"+id+"/"+idCursus+'/'+idCours;
		}
		@RequestMapping("/etatChapitre/{id}/{idCursus}/{idCours}/{idChapitre}")
		public String etatChapitre(@PathVariable(name = "id") Long id,@PathVariable(name = "idCursus") Long idCursus,@PathVariable(name = "idCours") Long idCours,@PathVariable(name = "idChapitre") Long idChapitre) {
			Chapitre chapitre= new Chapitre();
			chapitre.setId(idChapitre);
			serviceChapitre.setEtatChapitre(chapitre);
			return "redirect:/ChapitreDetails/"+id+"/"+idCursus+'/'+idCours+"/"+chapitre.getId();
		}
		
		//section formateur
		@RequestMapping("/sectionFormateur/{id}/{idCursus}/{idCours}/{idChapitre}")
		public String sectionFormateur(@PathVariable(name = "id") Long id,@PathVariable(name = "idCursus") Long idCursus,@PathVariable(name = "idCours") Long idCours,@PathVariable(name = "idChapitre") Long idChapitre,Model model) {
			Section section = new Section();
			section.setId_Chapitre(idChapitre);
			List<Section> listSectionFormateur= serviceSection.AllSectionChapitre(section);
			model.addAttribute("listSectionFormateur", listSectionFormateur);
			
			return "sectionFormateur";		
		}
		@RequestMapping("/ajoutSection/{id}/{idCursus}/{idCours}/{idChapitre}")
		public String ajoutSection(@PathVariable(name = "id") Long id,@PathVariable(name = "idCursus") Long idCursus,@PathVariable(name = "idCours") Long idCours,@PathVariable(name = "idChapitre") Long idChapitre) {
			/*Section section = new Section();
			section.setId_Formateur(id);*/
			
			
			return "ajoutSection";		
		}
		@RequestMapping(value = "/saveSection/{id}/{idCursus}/{idCours}/{idChapitre}", method = RequestMethod.POST)
		public String saveSection(@ModelAttribute("section") Section section,@PathVariable(name = "id") Long id,@PathVariable(name = "idCursus") Long idCursus,@PathVariable(name = "idCours") Long idCours,@PathVariable(name = "idChapitre") Long idChapitre) {
			section.setEtat(false);
			section.setId_Chapitre(idChapitre);
			Date date = new Date(); 
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println(formatter.format(date));
			section.setDate_ajout(formatter.format(date));
			Boolean test = serviceSection.save(section);
			
			return "redirect:/sectionFormateur/"+id+"/"+idCursus+"/"+idCours+"/"+section.getId_Chapitre();
		}
		@RequestMapping("/SectionDetails/{id}/{idCursus}/{idCours}/{idChapitre}/{idSection}")
		public String detailsSection(@PathVariable(name = "id") Long id,@PathVariable(name = "idSection") Long idSection,@PathVariable(name = "idCursus") Long idCursus,@PathVariable(name = "idCours") Long idCours,@PathVariable(name = "idChapitre") Long idChapitre,Model model) {
			Section cu = new Section();
			cu.setId(idSection);
			Section section = serviceSection.get(cu);
			model.addAttribute("section", section);
			
			return "SectionDetails";		
		}
		@RequestMapping(value = "/updateSection", method = RequestMethod.POST)
		public String updateSection(@ModelAttribute("section") Section section) {
			Section c = serviceSection.get(section);
			section.setEtat(c.getEtat());
			section.setDate_ajout(c.getDate_ajout());
		
			Chapitre ch= new Chapitre();
			ch.setId(c.getId_Chapitre());
			Chapitre chap = serviceChapitre.get(ch);
			section.setId_Chapitre(chap.getId());
			Long idCours = chap.getId_Cours();
			
			Cours cu = new Cours();
			cu.setId(idCours);
			Cours cur = serviceCours.get(cu);
			Cursus curs = new Cursus();
			curs.setId(cur.getId_Cursus());
			Long formateurId=serviceCursus.get(curs).getId_Formateur();
			serviceSection.update(section);
			
			return "redirect:/SectionDetails/"+formateurId+"/"+cur.getId_Cursus()+"/"+idCours+"/"+section.getId_Chapitre()+"/"+section.getId();
		}
		@RequestMapping("/deleteSection/{id}/{idCursus}/{idCours}/{idChapitre}/{idSection}")
		public String deleteSection(@PathVariable(name = "id") Long id,@PathVariable(name = "idCursus") Long idCursus,@PathVariable(name = "idSection") Long idSection,@PathVariable(name = "idCours") Long idCours,@PathVariable(name = "idChapitre") Long idChapitre) {
			Section section = new Section();
			section.setId(idSection);
			serviceSection.delete(section);
			return "redirect:/sectionFormateur/"+id+"/"+idCursus+"/"+idCours+"/"+idChapitre;
		}
		@RequestMapping("/etatSection/{id}/{idCursus}/{idCours}/{idChapitre}/{idSection}")
		public String etatSection(@PathVariable(name = "id") Long id,@PathVariable(name = "idCursus") Long idCursus,@PathVariable(name = "idCours") Long idCours,@PathVariable(name = "idSection") Long idSection,@PathVariable(name = "idChapitre") Long idChapitre) {
			Section section= new Section();
			section.setId(idSection);
			serviceSection.setEtatSection(section);
			return "redirect:/SectionDetails/"+id+"/"+idCursus+'/'+idCours+"/"+idChapitre+"/"+section.getId();
		}
		
		//etudiant
		
		@RequestMapping("/tousCursusActif/{id}")
		public String tousCursusActif(Model model,@PathVariable(name = "id") Long id) {
			/*Cursus cursus = new Cursus();
			cursus.setId_Formateur(id);*/
			List<Cursus> listCursusActif = serviceCursus.AllCursusActif();
			model.addAttribute("listCursusActif", listCursusActif);
			
			return "tousCursusActif";		
		}
		
		@RequestMapping("/ajoutCursusEtudiant/{id}/{idCursus}")
		public String ajoutCursusEtudiant(@PathVariable(name = "id") Long id,@PathVariable(name = "idCursus") Long idCursus) {
			Cursus_Suivis cs = new Cursus_Suivis();
			cs.setId_Cursus(idCursus);
			cs.setId_Utilisateur(id);
			Cursus cur = new Cursus();
			cur.setId(idCursus);
			Cours coursToAdd = serviceCours.FirstCoursCursus(cur);
			if(coursToAdd!=null) {
			cs.setId_Cours(coursToAdd.getId());
			
			Cours c = new Cours();
			c.setId(coursToAdd.getId());
			Chapitre chapitreToAdd = serviceChapitre.FirstChapitreCursus(c);
			if(chapitreToAdd!=null) {
			cs.setId_Chapitre(chapitreToAdd.getId());
			}
			}
			serviceCursus_Suivis.save(cs);
			return "redirect:/tousCursusActif/"+id;
		}
		
		@RequestMapping("/deleteCursusEtudiant/{id}/{idCursus}")
		public String deleteCursusEtudiant(@PathVariable(name = "id") Long id,@PathVariable(name = "idCursus") Long idCursus) {
			Cursus_Suivis cursus_suivis = new Cursus_Suivis();
			cursus_suivis.setId_Cursus(idCursus);
			cursus_suivis.setId_Utilisateur(id);
			Cursus_Suivis cursus_suivisToDelete = serviceCursus_Suivis.FindCursus_Suivis(cursus_suivis);
			serviceCursus_Suivis.delete(cursus_suivisToDelete);
			return "redirect:/CursusEtudiant/"+id;
		}
		
		
		
		
		@RequestMapping("/CursusEtudiant/{id}")
		public String CursusEtudiant(Model model,@PathVariable(name = "id") Long id) {
			/*Cursus cursus = new Cursus();
			cursus.setId_Formateur(id);*/
			List<Cursus> listCursusEtudiant = new ArrayList<Cursus>();
			
			Cursus_Suivis cursus_suivis = new Cursus_Suivis();
			cursus_suivis.setId_Utilisateur(id);
			List<Cursus_Suivis> listCursusSuivis = serviceCursus_Suivis.AllCursus_SuivisCursus(cursus_suivis);
			 for (Cursus_Suivis cs : listCursusSuivis) {
		         Long CursusId = cs.getId_Cursus();
		         Cursus cursus = new Cursus();
		         cursus.setId(CursusId);
		         listCursusEtudiant.add(serviceCursus.get(cursus));
		      }
			model.addAttribute("listCursusEtudiant", listCursusEtudiant);
			
			return "CursusEtudiant";		
		}
		
		
		
		
		
		
		
		// lire cursus etudiant
				@RequestMapping("/commencerCursusEtudiant/{id}/{idCursus}")
				public String commencerCursusEtudiant(Model model,@PathVariable(name = "id") Long id,@PathVariable(name = "idCursus") Long idCursus) {
					/*Cursus cursus = new Cursus();
					cursus.setId(idCursus);*/
					Cours cours = new Cours();
					cours.setId_Cursus(idCursus);
					List<Cours> listCours = serviceCours.AllCoursActifCursus(cours);
					model.addAttribute("listCours", listCours);
					/*Cursus cursus = new Cursus();
					cursus.setId_Formateur(id);*/
				/*	List<Cursus> listCursusEtudiant = new ArrayList<Cursus>();
					
					Cursus_Suivis cursus_suivis = new Cursus_Suivis();
					cursus_suivis.setId_Utilisateur(id);
					List<Cursus_Suivis> listCursusSuivis = serviceCursus_Suivis.AllCursus_SuivisCursus(cursus_suivis);
					 for (Cursus_Suivis cs : listCursusSuivis) {
				         Long CursusId = cs.getId_Cursus();
				         Cursus cursus = new Cursus();
				         cursus.setId(CursusId);
				         listCursusEtudiant.add(serviceCursus.get(cursus));
				      }
					model.addAttribute("listCursusEtudiant", listCursusEtudiant);*/
					
					return "CursusEtudiant";		
				}
}
