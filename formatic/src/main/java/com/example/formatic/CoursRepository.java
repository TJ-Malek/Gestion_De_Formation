package com.example.formatic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CoursRepository extends JpaRepository<Cours, Long>  {
	@Query("select c from Cours c where c.designation = ?1 and c.id_Cursus = ?2")
	public Cours FindCours(String designtation,Long id_Cursus);
	@Query("select c.etat from Cours c where c.id = ?1")
	public Cours getEtat(Long id);
	@Query("update Cours c  set c.etat =?1 where c.id = ?2")
	public Cours setEtat(Boolean etat,Long id);
	@Query("select c from Cours c where c.id_Cursus = ?1")
	public Cours AllCoursCursus(Long id_Cursus);
}
