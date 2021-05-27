package com.example.formatic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface FormateurRepository extends JpaRepository<Formateur, Long> {
	@Query("select f.etat from Formateur f where f.id = ?1")
	public Formateur getEtat(Long id);
	@Query("update Formateur f  set f.etat =?1 where f.id = ?2 ")
	public Formateur setEtat(Boolean etat,Long id);
}
