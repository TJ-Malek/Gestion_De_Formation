package com.example.formatic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CursusRepository  extends JpaRepository<Cursus, Long>  {
	@Query("select * from Cursus c where c.designation = ?1 and c.id_Formateur = ?2")
	public Cursus FindCursus(String designtation,Long id_Formateur);
	@Query("select c.etat from Cursus c where c.id = ?1")
	public Cursus getEtat(Long id);
	@Query("update Cursus c  set c.etat =?1 where c.id = ?2")
	public Cursus setEtat(Boolean etat,Long id);
	@Query("select * from Cursus c where c.id_Formateur = ?1")
	public Cursus AllCursusFormateur(Long id_Formateur);
	
}
