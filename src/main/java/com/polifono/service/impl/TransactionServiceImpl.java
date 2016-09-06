package com.polifono.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polifono.domain.Transaction;
import com.polifono.repository.ITransactionRepository;
import com.polifono.service.ITransactionService;

@Service
public class TransactionServiceImpl implements ITransactionService {

	private final ITransactionRepository transactionRepository;
	
	@Autowired
	public TransactionServiceImpl(final ITransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}
	
	public final Transaction save(Transaction transaction) {
		return transactionRepository.save(transaction);
	}
	
	public final Transaction find(int transactionId) {
		return transactionRepository.findOne(transactionId);
	}
	
	public final List<Transaction> findTransactionByCode(String code) {
		return transactionRepository.findTransactionByCode(code);
	}
}