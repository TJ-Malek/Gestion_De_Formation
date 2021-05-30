package com.example.formatic;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Cursus_SuivisRepository extends JpaRepository<Cursus_Suivis, Long>  {
	@Query("select cs from Cursus_Suivis cs where cs.id_Utilisateur = ?1 and cs.id_Cursus= ?2")
	public Cursus_Suivis FindCursus_Suivis(Long id_Utilisateur,Long iid_Cursus);
	@Query("select cs from Cursus_Suivis cs where cs.id_Utilisateur = ?1 order by date_ajout DESC")
	public List<Cursus_Suivis> AllCursus_Suivis(Long id_Utilisateur);
}
