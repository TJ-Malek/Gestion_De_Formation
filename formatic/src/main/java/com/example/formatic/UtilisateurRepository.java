package com.example.formatic;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

//import persistence.Utilisateur;


public interface  UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
	@Query("select u from Utilisateur u where u.email = ?1 AND u.mdp =  ?2")
	public Utilisateur findUtilisateurByEmailAndMdp(String email,String mdp);
	@Query("select u from Utilisateur u where u.email = ?1")
	public Utilisateur findUtilisateur(String email);
}
