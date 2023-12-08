package Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Models.CustomerModel;


@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Integer> {

}

