package com.etiya.rentacar.business.concretes;

import com.etiya.rentacar.business.abstracts.BrandService;
import com.etiya.rentacar.business.dtos.requests.BrandRequests.CreateBrandRequest;
import com.etiya.rentacar.business.dtos.requests.BrandRequests.UpdateBrandRequest;
import com.etiya.rentacar.business.dtos.responses.BrandResponses.*;
import com.etiya.rentacar.business.rules.BrandBusinessRules;
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
    private BrandBusinessRules brandBusinessRules;
    private ModelMapperService modelMapperService;

    @Override
    public CreatedBrandResponse add(CreateBrandRequest createBrandRequest) {
        brandBusinessRules.brandNameCannotBeDuplicated(createBrandRequest.getName());

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
        List<GetBrandsResponse> allBrands = brands.stream()
                .filter(brand -> brand.getDeletedDate() == null)
                .map(brand ->
                        modelMapperService.forResponse().map(brand, GetBrandsResponse.class)).collect(Collectors.toList());

        return allBrands;
    }

    @Override
    public GetBrandsResponse findById(long id) {
        brandBusinessRules.brandNotFound(id);
        brandBusinessRules.deletedBrand(id);
        Optional<Brand> foundBrand = brandRepository.findById(id);

        GetBrandsResponse getBrandsResponse = modelMapperService.forResponse()
                .map(foundBrand.get(), GetBrandsResponse.class);

        return getBrandsResponse;
    }

    @Override
    public UpdatedBrandResponse update(UpdateBrandRequest updateBrandRequest, long id) {
        brandBusinessRules.brandNotFound(id);
        brandBusinessRules.deletedBrand(id);
        brandBusinessRules.brandNameCannotBeDuplicated(updateBrandRequest.getName());

        Brand foundBrand = brandRepository.findById(id).orElse(null);
        modelMapperService.forRequest().map(updateBrandRequest, foundBrand);
        foundBrand.setUpdatedDate(LocalDateTime.now());
        Brand updatedBrand = brandRepository.save(foundBrand);

        return modelMapperService.forResponse().map(updatedBrand, UpdatedBrandResponse.class);
    }

    @Override
    public DeletedBrandResponse delete(long id) {
        brandBusinessRules.brandNotFound(id);
        Brand foundBrand = brandRepository.findById(id).orElse(null);
        foundBrand.setDeletedDate(LocalDateTime.now());
        foundBrand.setId(id);

        Brand deletedBrand = brandRepository.save(foundBrand);
        DeletedBrandResponse deletedBrandResponse = modelMapperService.forResponse()
                .map(deletedBrand, DeletedBrandResponse.class);

        return deletedBrandResponse;
    }
}
