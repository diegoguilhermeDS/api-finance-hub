package br.com.diego.apifinancehub.repository;

import br.com.diego.apifinancehub.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
