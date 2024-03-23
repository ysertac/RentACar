package com.etiya.rentacar.business.concretes;

import com.etiya.rentacar.business.abstracts.BrandService;
import com.etiya.rentacar.business.dtos.requests.BrandRequests.CreateBrandRequest;
import com.etiya.rentacar.business.dtos.requests.BrandRequests.UpdateBrandRequest;
import com.etiya.rentacar.business.dtos.responses.BrandResponses.*;
import com.etiya.rentacar.dataAccess.abstracts.BrandRepository;
import com.etiya.rentacar.entities.Brand;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class BrandManager implements BrandService {
    private BrandRepository brandRepository;

    public BrandManager(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public CreatedBrandResponse add(CreateBrandRequest createBrandRequest) throws Exception {
        List<Brand> foundBrand = brandRepository.findByName(createBrandRequest.getName());
        CreatedBrandResponse createdBrandResponse = new CreatedBrandResponse();
        if (foundBrand.isEmpty()) {
        Brand brand = new Brand();
        brand.setName(createBrandRequest.getName());
        brand.setCreatedDate(LocalDateTime.now());
        Brand createdBrand = brandRepository.save(brand);

        createdBrandResponse.setId(createdBrand.getId());
        createdBrandResponse.setName(createdBrand.getName());
        createdBrandResponse.setCreateDate(createdBrand.getCreatedDate());
        return createdBrandResponse;

        } else {
            throw new Exception(foundBrand.get(0).getName() + " is already exists");
        }
    }

    @Override
    public GetBrandsResponse findAll() {
        List<Brand> brands = brandRepository.findAll();
        List<String> brandNames = new LinkedList<>();
        GetBrandsResponse getBrandsResponse = new GetBrandsResponse();
        for (Brand b : brands) {
            brandNames.add(b.getName());
        }
        getBrandsResponse.setBrands(brandNames);

        return getBrandsResponse;
    }

    @Override
    public GetBrandResponse findById(long id) {
        Optional<Brand> foundBrand = brandRepository.findById(id);

        GetBrandResponse getBrandResponse = new GetBrandResponse();
        getBrandResponse.setId(foundBrand.get().getId());
        getBrandResponse.setName(foundBrand.get().getName());
        getBrandResponse.setCreatedTime(foundBrand.get().getCreatedDate());
        getBrandResponse.setUpdatedTime(foundBrand.get().getUpdatedDate());
        getBrandResponse.setDeletedTime(foundBrand.get().getDeletedDate());

        return getBrandResponse;
    }

    @Override
    public Brand findByName(String name) {
        return brandRepository.findByName(name).get(0);
    }

    @Override
    public UpdatedBrandResponse update(UpdateBrandRequest updateBrandRequest, long id) {
        Optional<Brand> foundBrand = brandRepository.findById(id);
        foundBrand.get().setId(id);
        foundBrand.get().setName(updateBrandRequest.getName());
        foundBrand.get().setUpdatedDate(LocalDateTime.now());
        UpdatedBrandResponse updatedBrandResponse = new UpdatedBrandResponse();
        updatedBrandResponse.setId(foundBrand.get().getId());
        updatedBrandResponse.setName(foundBrand.get().getName());
        updatedBrandResponse.setCreateDate(foundBrand.get().getCreatedDate());
        updatedBrandResponse.setUpdateDate(foundBrand.get().getUpdatedDate());

        brandRepository.save(foundBrand.get());
        return updatedBrandResponse;
    }

    @Override
    public DeletedBrandResponse delete(long id) {
        Optional<Brand> foundBrand = brandRepository.findById(id);

        DeletedBrandResponse deletedBrandResponse = new DeletedBrandResponse();
        deletedBrandResponse.setId(foundBrand.get().getId());
        deletedBrandResponse.setName(foundBrand.get().getName());
        deletedBrandResponse.setCreatedDate(foundBrand.get().getCreatedDate());
        deletedBrandResponse.setUpdatedDate(foundBrand.get().getUpdatedDate());
        deletedBrandResponse.setDeletedDate(foundBrand.get().getDeletedDate());

        brandRepository.delete(foundBrand.get());
        return deletedBrandResponse;

    }

}
