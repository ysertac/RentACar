package com.etiya.rentacar.business.concretes;

import com.etiya.rentacar.business.abstracts.BrandService;
import com.etiya.rentacar.business.dtos.requests.BrandRequests.CreateBrandRequest;
import com.etiya.rentacar.business.dtos.requests.BrandRequests.UpdateBrandRequest;
import com.etiya.rentacar.business.dtos.responses.BrandResponses.*;
import com.etiya.rentacar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentacar.dataAccess.abstracts.BrandRepository;
import com.etiya.rentacar.entities.Brand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {
    private BrandRepository brandRepository;
    private ModelMapperService modelMapperService;

    @Override
    public CreatedBrandResponse add(CreateBrandRequest createBrandRequest) {
        Brand brand = modelMapperService.forRequest().map(createBrandRequest, Brand.class);
        brand.setCreatedDate(LocalDateTime.now());

        Brand createdBrand = brandRepository.save(brand);

        CreatedBrandResponse createdBrandResponse = modelMapperService.forResponse()
                .map(createdBrand, CreatedBrandResponse.class);

        return createdBrandResponse;
    }

    @Override
    public List<GetBrandsResponse> findAll() {
        List<Brand> brands = brandRepository.findAll();
        List<GetBrandsResponse> allBrands = brands.stream().map(brand ->
                modelMapperService.forResponse().map(brand, GetBrandsResponse.class)).collect(Collectors.toList());

        return allBrands;
    }

    @Override
    public GetBrandsResponse findById(long id) {
        Optional<Brand> foundBrand = brandRepository.findById(id);

        GetBrandsResponse getBrandsResponse = modelMapperService.forResponse()
                .map(foundBrand.get(), GetBrandsResponse.class);

        return getBrandsResponse;
    }

    @Override
    public UpdatedBrandResponse update(UpdateBrandRequest updateBrandRequest, long id) throws Exception {
        Brand foundBrand = brandRepository.findById(id).orElse(null);
        if (foundBrand != null){
            modelMapperService.forRequest().map(updateBrandRequest, foundBrand);
            foundBrand.setUpdatedDate(LocalDateTime.now());
            Brand updatedBrand = brandRepository.save(foundBrand);

            return modelMapperService.forResponse().map(updatedBrand, UpdatedBrandResponse.class);
        } else {
            throw new Exception("There is no brand with this id");
        }
    }

    @Override
    public DeletedBrandResponse delete(long id) {
        Brand foundBrand = brandRepository.findById(id).orElse(null);
        foundBrand.setDeletedDate(LocalDateTime.now());

        DeletedBrandResponse deletedBrandResponse = modelMapperService.forResponse()
                .map(foundBrand, DeletedBrandResponse.class);

        brandRepository.delete(foundBrand);
        return deletedBrandResponse;
    }
}
