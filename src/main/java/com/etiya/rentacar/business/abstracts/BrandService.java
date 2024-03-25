package com.etiya.rentacar.business.abstracts;

import com.etiya.rentacar.business.dtos.requests.BrandRequests.CreateBrandRequest;
import com.etiya.rentacar.business.dtos.requests.BrandRequests.UpdateBrandRequest;
import com.etiya.rentacar.business.dtos.responses.BrandResponses.*;

import java.util.List;

public interface BrandService {
    CreatedBrandResponse add(CreateBrandRequest brand) throws Exception;

    List<GetBrandsResponse> findAll();

    GetBrandsResponse findById(long id);

    UpdatedBrandResponse update(UpdateBrandRequest updateBrandRequest, long id) throws Exception;

    DeletedBrandResponse delete(long id);
}
