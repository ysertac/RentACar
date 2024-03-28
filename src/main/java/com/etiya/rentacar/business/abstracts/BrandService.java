package com.etiya.rentacar.business.abstracts;

import com.etiya.rentacar.business.dtos.requests.brandRequests.CreateBrandRequest;
import com.etiya.rentacar.business.dtos.requests.brandRequests.UpdateBrandRequest;
import com.etiya.rentacar.business.dtos.responses.brandResponses.*;

import java.util.List;

public interface BrandService {
    CreatedBrandResponse add(CreateBrandRequest brand);

    List<GetBrandsResponse> findAll();

    GetBrandsResponse findById(long id);

    UpdatedBrandResponse update(UpdateBrandRequest updateBrandRequest, long id);

    DeletedBrandResponse delete(long id);
}
