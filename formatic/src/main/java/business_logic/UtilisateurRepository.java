package business_logic;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import persistence.Utilisateur;


public interface  UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
	@Query(value = "from utilisateur t where email = :email AND mdp =  :mdp")
	public Utilisateur findUtilisateur(@Param("email")String email,@Param("mdp")String mdp);

}
