package com.example.formatic;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CursusRepository  extends JpaRepository<Cursus, Long>  {
	@Query("select c from Cursus c where c.designation = ?1 and c.id_Formateur = ?2")
	public Cursus FindCursus(String designtation,Long id_Formateur);
	@Query("select c.etat from Cursus c where c.id = ?1")
	public  Boolean getEtat(Long id);
	@Modifying
	@Query("update Cursus c  set c.etat =?1 where c.id = ?2")
	public void setEtat(Boolean etat,Long id);
	@Query("select c from Cursus c where c.id_Formateur = ?1 order by c.date_ajout DESC")
	public List<Cursus> AllCursusFormateur(Long id_Formateur);
	@Query("select c from Cursus c where c.etat=1 order by c.date_ajout DESC")
	public List<Cursus> AllCursusActif();
	
}
