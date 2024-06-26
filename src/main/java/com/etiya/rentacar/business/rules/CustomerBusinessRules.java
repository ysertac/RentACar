package com.etiya.rentacar.business.rules;

import com.etiya.rentacar.business.messages.CustomerMessages;
import com.etiya.rentacar.core.exceptions.types.BusinessException;
import com.etiya.rentacar.dataAccess.abstracts.CustomerRepository;
import com.etiya.rentacar.entities.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerBusinessRules {
    private CustomerRepository customerRepository;

    public void customerUsernameCannotBeDuplicated(String username) {
        Customer foundCustomer = customerRepository.findByUsernameIgnoreCase(username);

        if (foundCustomer != null && foundCustomer.getDeletedDate() == null) {
            throw new BusinessException(CustomerMessages.customerUsernameCannotBeDuplicated);
        }

    }

    public void customerNotFound(long customerId) {
        Optional<Customer> foundCustomer = customerRepository.findById(customerId);

        if (!foundCustomer.isPresent()) {
            throw new BusinessException(CustomerMessages.customerNotFound);
        }

    }

    public void deletedCustomer(long customerId) {
        Customer foundCustomer = customerRepository.findById(customerId).orElse(null);
        if (foundCustomer.getDeletedDate() != null) {
            throw new BusinessException(CustomerMessages.deletedCustomer);
        }
    }
}