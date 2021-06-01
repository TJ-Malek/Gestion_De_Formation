package com.example.formatic;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ChapitreRepository extends JpaRepository<Chapitre, Long>  {
	@Query("select c from Chapitre c where c.designation = ?1 and c.id_Cours = ?2")
	public Chapitre FindChapitre(String designtation,Long id_Cours);
	@Query("select c.etat from Chapitre c where c.id = ?1")
	public  Boolean getEtat(Long id);
	@Modifying
	@Query("update Chapitre c  set c.etat =?1 where c.id = ?2")
	public void setEtat(Boolean etat,Long id);
	@Query("select c from Chapitre c where c.id_Cours  = ?1 order by c.date_ajout DESC")
	public List<Chapitre> AllChapitreCours(Long id_Cours);
	@Query("select c from Chapitre c where c.id_Cours  = ?1 AND c.etat=1 order by c.date_ajout DESC")
	public List<Chapitre> AllChapitreActifCours(Long id_Cours);
	@Query("select c from Chapitre c where c.id_Cours = ?1 and c.etat=1 and c.date_ajout in (select MIN(c.date_ajout) from c)")
	public Chapitre FirstChapitreCours(Long id_Cours);
	@Query(nativeQuery = true,value="select c from Chapitre c where c.id_Cours = ?1 and c.etat=1 and c.date_ajout > ?2 order by c.date_ajout ASC LIMIT 1")
	public Chapitre ClosestChapitreCours(Long id_Cours,String date_ajout);
}
