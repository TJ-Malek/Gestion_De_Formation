package com.example.formatic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChapitreRepository extends JpaRepository<Chapitre, Long>  {
	@Query("select * from Chapitre c where c.designation = ?1 and c.id_Cours = ?2")
	public Chapitre FindChapitre(String designtation,Long id_Cours);
	@Query("select c.etat from Chapitre c where c.id = ?1")
	public Chapitre getEtat(Long id);
	@Query("update Chapitre c  set c.etat =?1 where c.id = ?2")
	public Chapitre setEtat(Boolean etat,Long id);
	@Query("select * from Chapitre c where c.id_Cours  = ?1")
	public Chapitre AllChapitreCours(Long id_Cours);
}
