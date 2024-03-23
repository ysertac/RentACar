package com.etiya.rentacar.business.abstracts;

import com.etiya.rentacar.business.dtos.requests.BrandRequests.CreateBrandRequest;
import com.etiya.rentacar.business.dtos.requests.BrandRequests.UpdateBrandRequest;
import com.etiya.rentacar.business.dtos.responses.BrandResponses.*;
import com.etiya.rentacar.entities.Brand;

public interface BrandService {
    CreatedBrandResponse add(CreateBrandRequest brand) throws Exception;

    GetBrandsResponse findAll();

    GetBrandResponse findById(long id);

    Brand findByName(String name);

    UpdatedBrandResponse update(UpdateBrandRequest updateBrandRequest, long id);

    DeletedBrandResponse delete(long id);
}
