package back.end.assignment.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import back.end.assignment.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}