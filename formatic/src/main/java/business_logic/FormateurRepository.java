package business_logic;

import org.springframework.data.jpa.repository.JpaRepository;

import persistence.Formateur;

public interface FormateurRepository extends JpaRepository<Formateur, Long> {

}
