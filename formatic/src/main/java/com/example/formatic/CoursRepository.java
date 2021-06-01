package com.example.formatic;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CoursRepository extends JpaRepository<Cours, Long>  {
	@Query("select c from Cours c where c.designation = ?1 and c.id_Cursus = ?2")
	public Cours FindCours(String designtation,Long id_Cursus);
	@Query("select c.etat from Cours c where c.id = ?1")
	public Boolean getEtat(Long id);
	@Modifying
	@Query("update Cours c  set c.etat =?1 where c.id = ?2")
	public void setEtat(Boolean etat,Long id);
	@Query("select c from Cours c where c.id_Cursus = ?1 order by c.date_ajout DESC")
	public List<Cours> AllCoursCursus(Long id_Cursus);
	@Query("select c from Cours c where c.id_Cursus = ?1 and c.etat=1 order by c.date_ajout DESC")
	public List<Cours> AllCoursActifCursus(Long id_Cursus);
	@Query("select c from Cours c where c.id_Cursus = ?1 and c.etat=1 and c.date_ajout in (select MIN(c.date_ajout) from c)")
	public Cours FirstCoursCursus(Long id_Cursus);
	@Query(nativeQuery = true,value="select c from Cours c where c.id_Cursus = ?1 and c.etat=1 and c.date_ajout > ?2 order by c.date_ajout ASC LIMIT 1")
	public Cours ClosestCoursCursus(Long id_Cursus,String date_ajout);
}
