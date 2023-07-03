package br.com.diego.apifinancehub.service.impl;

import br.com.diego.apifinancehub.dto.CreateTransactionDto;
import br.com.diego.apifinancehub.exception.AppException;
import br.com.diego.apifinancehub.model.Transaction;
import br.com.diego.apifinancehub.model.User;
import br.com.diego.apifinancehub.repository.TransactionRepository;
import br.com.diego.apifinancehub.repository.UserRepository;
import br.com.diego.apifinancehub.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    public Transaction createTransaction(final CreateTransactionDto transactionData) {
        final User findPayer = userRepository.findById(transactionData.getPayer_id()).orElseThrow(() -> new AppException("payerNotFound", HttpStatus.NOT_FOUND));;

        if (Objects.equals(findPayer.getType(), "SELLER")) {
            throw  new AppException("invalidUserType", HttpStatus.FORBIDDEN);
        }

        final User findPayee = userRepository.findById(transactionData.getPayee_id()).orElseThrow(() -> new AppException("payeeNotFound", HttpStatus.NOT_FOUND));;

        final float transactionValue = transactionData.getValue();

        final float payerCurrentBalance = findPayer.getBalance();
        final float payeeCurrentBalance = findPayee.getBalance();

        if (payerCurrentBalance < transactionValue){
            throw  new AppException("balanceNotSufficient", HttpStatus.FORBIDDEN);
        }

        findPayer.setBalance(payerCurrentBalance - transactionValue);
        findPayee.setBalance(payeeCurrentBalance + transactionValue);


        final Transaction newTransaction = new Transaction(findPayer, findPayee, transactionValue);

        return transactionRepository.save(newTransaction);
    }

    public Transaction reatriveTransaction(final long id) {
        return transactionRepository.findById(id).orElseThrow(() -> new AppException("transactionNotFound", HttpStatus.NOT_FOUND));
    }
}
