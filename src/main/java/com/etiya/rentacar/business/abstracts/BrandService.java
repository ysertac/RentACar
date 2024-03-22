package com.etiya.rentacar.business.abstracts;

import com.etiya.rentacar.business.dtos.requests.CreateBrandRequest;
import com.etiya.rentacar.business.dtos.requests.UpdateBrandRequest;
import com.etiya.rentacar.business.dtos.responses.*;
import org.hibernate.sql.Update;

public interface BrandService {
    CreatedBrandResponse add(CreateBrandRequest brand);

    GetBrandsResponse findAll();

    GetBrandResponse findById(long id);

    UpdatedBrandResponse update(UpdateBrandRequest updateBrandRequest, long id);

    DeletedBrandResponse delete(long id);
}
