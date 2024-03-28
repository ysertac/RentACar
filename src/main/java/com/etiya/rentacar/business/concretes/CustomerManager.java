package com.etiya.rentacar.business.concretes;

import com.etiya.rentacar.business.abstracts.CustomerService;
import com.etiya.rentacar.business.dtos.requests.customerRequests.CreateCustomerRequest;
import com.etiya.rentacar.business.dtos.requests.customerRequests.UpdateCustomerRequest;
import com.etiya.rentacar.business.dtos.responses.customerResponses.CreatedCustomerResponses;
import com.etiya.rentacar.business.dtos.responses.customerResponses.DeletedCustomerResponse;
import com.etiya.rentacar.business.dtos.responses.customerResponses.GetCustomerResponse;
import com.etiya.rentacar.business.dtos.responses.customerResponses.UpdatedCustomerResponse;
import com.etiya.rentacar.business.rules.CustomerBusinessRules;
import com.etiya.rentacar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentacar.dataAccess.abstracts.CustomerRepository;
import com.etiya.rentacar.entities.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerManager implements CustomerService {
    private CustomerRepository customerRepository;
    private ModelMapperService modelMapperService;
    private CustomerBusinessRules customerBusinessRules;

    @Override
    public CreatedCustomerResponses add(CreateCustomerRequest createCustomerRequest) {
        customerBusinessRules.customerUsernameCannotBeDuplicated(createCustomerRequest.getUsername());

        Customer customer = modelMapperService.forRequest().map(createCustomerRequest, Customer.class);
        Customer createdCustomer = customerRepository.save(customer);

        CreatedCustomerResponses createdCustomerResponses =
                modelMapperService.forResponse().map(createdCustomer, CreatedCustomerResponses.class);
        return createdCustomerResponses;
    }

    @Override
    public List<GetCustomerResponse> findAll() {
        List<Customer> allCustomers = customerRepository.findAll();
        List<GetCustomerResponse> getCustomerResponses = allCustomers.stream().filter(customer -> customer.getDeletedDate() == null)
                .map(customer -> modelMapperService.forResponse().map(customer, GetCustomerResponse.class)).collect(Collectors.toList());
        return getCustomerResponses;
    }

    @Override
    public GetCustomerResponse findById(long id) {
        customerBusinessRules.customerNotFound(id);
        customerBusinessRules.deletedCustomer(id);

        Customer foundCustomer = customerRepository.findById(id).orElse(null);
        GetCustomerResponse getCustomerResponse = modelMapperService.forResponse().map(foundCustomer, GetCustomerResponse.class);
        return getCustomerResponse;
    }

    @Override
    public UpdatedCustomerResponse update(UpdateCustomerRequest updateCustomerRequest, long id) {
        customerBusinessRules.customerNotFound(id);
        customerBusinessRules.deletedCustomer(id);
        customerBusinessRules.customerUsernameCannotBeDuplicated(updateCustomerRequest.getUsername());

        Customer foundCustomer = customerRepository.findById(id).get();
        Customer customer = modelMapperService.forRequest().map(updateCustomerRequest, Customer.class);
        customer.setId(id);
        customer.setCreatedDate(foundCustomer.getCreatedDate());
        customer.setUpdatedDate(LocalDateTime.now());

        UpdatedCustomerResponse updatedCustomerResponse =
                modelMapperService.forResponse().map(customer, UpdatedCustomerResponse.class);
        return updatedCustomerResponse;
    }

    @Override
    public DeletedCustomerResponse delete(long id) {
        customerBusinessRules.customerNotFound(id);
        customerBusinessRules.deletedCustomer(id);

        Customer foundCustomer = customerRepository.findById(id).get();
        foundCustomer.setId(id);
        Customer deletedCustomer = customerRepository.save(foundCustomer);

        DeletedCustomerResponse deletedCustomerResponse =
                modelMapperService.forResponse().map(deletedCustomer, DeletedCustomerResponse.class);
        return deletedCustomerResponse;
    }
}
