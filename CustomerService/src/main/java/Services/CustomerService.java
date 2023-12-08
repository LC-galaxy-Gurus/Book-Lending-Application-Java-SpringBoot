package Services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Models.CustomerModel;
import Repositories.CustomerRepository;

@Service
public class CustomerService 
{
    @Autowired
    private CustomerRepository customerRepository;

    public List<CustomerModel> getAllCustomers() 
    {
        return customerRepository.findAll();
    }
    
    public CustomerModel getCustomer(int id) 
    {
        return customerRepository.findById(id).orElse(null);
    }

    public CustomerModel saveCustomer(CustomerModel customer) 
    {
        return customerRepository.save(customer);
    }

    public void deleteCustomer(int id) 
    {
        customerRepository.deleteById(id);
    }
}
