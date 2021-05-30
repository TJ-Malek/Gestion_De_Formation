package com.example.formatic;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SectionRepository extends JpaRepository<Section, Long>  {
	@Query("select s from Section s where s.designation = ?1 and s.id_Chapitre = ?2")
	public Section FindSection(String designtation,Long id_Chapitre);
	@Query("select s.etat from Section s where s.id = ?1")
	public  Boolean getEtat(Long id);
	@Modifying
	@Query("update Section s  set s.etat =?1 where s.id = ?2")
	public void setEtat(Boolean etat,Long id);
	@Query("select s from Section s where s.id_Chapitre  = ?1 order by date_ajout DESC")
	public List<Section> AllSectionChapitre(Long id_Chapitre);
}
