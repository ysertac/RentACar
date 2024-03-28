package com.etiya.rentacar.business.abstracts;

import com.etiya.rentacar.business.dtos.requests.customerRequests.CreateCustomerRequest;
import com.etiya.rentacar.business.dtos.requests.customerRequests.UpdateCustomerRequest;
import com.etiya.rentacar.business.dtos.responses.customerResponses.CreatedCustomerResponses;
import com.etiya.rentacar.business.dtos.responses.customerResponses.DeletedCustomerResponse;
import com.etiya.rentacar.business.dtos.responses.customerResponses.GetCustomerResponse;
import com.etiya.rentacar.business.dtos.responses.customerResponses.UpdatedCustomerResponse;

import java.util.List;

public interface CustomerService {
    CreatedCustomerResponses add(CreateCustomerRequest createCustomerRequest);
    List<GetCustomerResponse> findAll();
    GetCustomerResponse findById(long id);
    UpdatedCustomerResponse update(UpdateCustomerRequest updateCustomerRequest, long id);
    DeletedCustomerResponse delete(long id);
}
