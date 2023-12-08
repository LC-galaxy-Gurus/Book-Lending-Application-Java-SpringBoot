package Services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Models.TransactionModel;
import Repositories.TransactionRepository;

@Service
public class TransactionService 
{
    @Autowired
    private TransactionRepository transactionRepository;

    public List<TransactionModel> getAllTransactions() 
    {
        return transactionRepository.findAll();
    }
    
    public TransactionModel getTransaction(int id) 
    {
        return transactionRepository.findById(id).orElse(null);
    }

    public TransactionModel saveTransaction(TransactionModel transaction) 
    {
        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(int id) 
    {
        transactionRepository.deleteById(id);
    }
}
