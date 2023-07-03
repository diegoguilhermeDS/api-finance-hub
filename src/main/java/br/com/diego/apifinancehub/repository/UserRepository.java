package br.com.diego.apifinancehub.repository;

import br.com.diego.apifinancehub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsUserByCpf(final String cpf);

    boolean existsUserByEmail(final String email);


}