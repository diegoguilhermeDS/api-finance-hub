package br.com.diego.apifinancehub.controller;

import br.com.diego.apifinancehub.dto.CreateTransactionDto;
import br.com.diego.apifinancehub.model.Transaction;
import br.com.diego.apifinancehub.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@Valid @RequestBody final CreateTransactionDto transactionData) {
        final Transaction newTransaction = transactionService.createTransaction(transactionData);

        return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> reatrieveTransaction(@PathVariable final String id) {
        final Transaction transaction = transactionService.reatriveTransaction(Long.parseLong(id));

        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }
}
