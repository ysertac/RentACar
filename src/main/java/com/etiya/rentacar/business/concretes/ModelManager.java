package com.etiya.rentacar.business.concretes;

import com.etiya.rentacar.business.abstracts.ModelService;
import com.etiya.rentacar.business.dtos.requests.CreateModelRequest;
import com.etiya.rentacar.business.dtos.responses.CreatedModelResponse;
import com.etiya.rentacar.dataAccess.abstracts.BrandRepository;
import com.etiya.rentacar.dataAccess.abstracts.FuelRepository;
import com.etiya.rentacar.dataAccess.abstracts.ModelRepository;
import com.etiya.rentacar.dataAccess.abstracts.TransmissionRepository;
import com.etiya.rentacar.entities.Brand;
import com.etiya.rentacar.entities.Fuel;
import com.etiya.rentacar.entities.Model;
import com.etiya.rentacar.entities.Transmission;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {
    private ModelRepository modelRepository;
    private BrandRepository brandRepository;
    private FuelRepository fuelRepository;
    private TransmissionRepository transmissionRepository;

    @Override
    public CreatedModelResponse add(CreateModelRequest createModelRequest) {
        Model model = new Model();
        Brand foundBrand = brandRepository.findByName(createModelRequest.getBrandName()).get(0);
        Fuel foundFuel = fuelRepository.findByName(createModelRequest.getFuelName()).get(0);
        Transmission foundTransmission = transmissionRepository.findByName(createModelRequest.getTransmissionName()).get(0);
        CreatedModelResponse createdModelResponse = new CreatedModelResponse();

        model.setName(createModelRequest.getName());
        model.setCreatedDate(LocalDateTime.now());
        model.setBrand(foundBrand);
        model.setFuel(foundFuel);
        model.setTransmission(foundTransmission);
        Model createdModel = modelRepository.save(model);

        createdModelResponse.setId(createdModel.getId());
        createdModelResponse.setName(createdModel.getName());
        createdModelResponse.setCreatedTime(createdModel.getCreatedDate());
        return createdModelResponse;
    }
}
