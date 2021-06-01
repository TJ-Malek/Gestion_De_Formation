package com.example.formatic;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface Cursus_SuivisRepository extends JpaRepository<Cursus_Suivis, Long>  {
	@Query("select cs from Cursus_Suivis cs where cs.id_Utilisateur = ?1 and cs.id_Cursus= ?2")
	public Cursus_Suivis FindCursus_Suivis(Long id_Utilisateur,Long id_Cursus);
	@Query("select cs from Cursus_Suivis cs where cs.id_Utilisateur = ?1 ")
	public List<Cursus_Suivis> AllCursus_Suivis(Long id_Utilisateur);
	@Modifying
	@Query("update Cursus_Suivis  cs  set cs.id_Cours=?1 where cs.id_Cursus = ?2 ")
	public void updateAllCursus_SuivisCours(Long id_Cours,Long id_Cursus );
	@Modifying
	@Query("update Cursus_Suivis  cs  set cs.id_Chapitre=?1 where cs.id_Cours = ?2 ")
	public void updateAllCursus_SuivisChapitre(Long id_Chapitre,Long id_Cours );
}
