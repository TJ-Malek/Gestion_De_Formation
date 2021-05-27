package business_logic;

import org.springframework.data.jpa.repository.JpaRepository;

import persistence.Utilisateur;


public interface  UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

}
