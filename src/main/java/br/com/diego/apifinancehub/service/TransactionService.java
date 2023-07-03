package br.com.diego.apifinancehub.service;

import br.com.diego.apifinancehub.dto.CreateTransactionDto;
import br.com.diego.apifinancehub.model.Transaction;

public interface TransactionService {

    Transaction createTransaction(final CreateTransactionDto transactionData);

    Transaction reatriveTransaction(final long id);
}
