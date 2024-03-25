package com.etiya.rentacar.business.concretes;

import com.etiya.rentacar.business.abstracts.BrandService;
import com.etiya.rentacar.business.abstracts.FuelService;
import com.etiya.rentacar.business.abstracts.ModelService;
import com.etiya.rentacar.business.abstracts.TransmissionService;
import com.etiya.rentacar.business.dtos.requests.ModelRequests.CreateModelRequest;
import com.etiya.rentacar.business.dtos.requests.ModelRequests.UpdateModelRequest;
import com.etiya.rentacar.business.dtos.responses.ModelResponses.CreatedModelResponse;
import com.etiya.rentacar.business.dtos.responses.ModelResponses.DeletedModelResponse;
import com.etiya.rentacar.business.dtos.responses.ModelResponses.GetModelsResponse;
import com.etiya.rentacar.business.dtos.responses.ModelResponses.UpdatedModelResponse;
import com.etiya.rentacar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentacar.dataAccess.abstracts.ModelRepository;
import com.etiya.rentacar.entities.Model;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {
    private ModelRepository modelRepository;
    private ModelMapperService modelMapperService;
    private BrandService brandService;
    private FuelService fuelService;
    private TransmissionService transmissionService;

    @Override
    public Model findByName(String name) {
        return modelRepository.findByName(name).get(0);
    }

    @Override
    public GetModelsResponse findById(long id) {
        Model foundModel = modelRepository.findById(id).orElse(null);
        GetModelsResponse getModelsResponse = modelMapperService.forResponse()
                .map(foundModel, GetModelsResponse.class);
        return getModelsResponse;
    }

    @Override
    public List<GetModelsResponse> findAll() {
        List<Model> allModels = modelRepository.findAll();
        List<GetModelsResponse> getModelsResponse = allModels.stream().map(model -> modelMapperService.forResponse()
                .map(model, GetModelsResponse.class)).collect(Collectors.toList());
        return getModelsResponse;
    }

    @Override
    public CreatedModelResponse add(CreateModelRequest createModelRequest) {
        Model model = modelMapperService.forRequest().map(createModelRequest, Model.class);
        model.setCreatedDate(LocalDateTime.now());
        Model createdModel =  modelRepository.save(model);

        CreatedModelResponse createdModelResponse = modelMapperService.forResponse()
                .map(createdModel,CreatedModelResponse.class);

        return createdModelResponse;
    }

    @Override
    public UpdatedModelResponse update(UpdateModelRequest updateModelRequest, long id) throws Exception {
        Model foundModel = modelRepository.findById(id).orElse(null);
        if (foundModel != null){
        Model model = modelMapperService.forRequest().map(updateModelRequest, Model.class);
        model.setId(id);
        model.setCreatedDate(foundModel.getCreatedDate());
        model.setUpdatedDate(LocalDateTime.now());
        Model updatedModel = modelRepository.save(model);

        return modelMapperService.forResponse().map(updatedModel, UpdatedModelResponse.class);
        } else {
           throw new Exception("There is no model with this id");
        }
    }

    @Override
    public DeletedModelResponse delete(long id) {
        Model foundModel = modelRepository.findById(id).orElse(null);
        DeletedModelResponse deletedModelResponse = modelMapperService.forResponse()
                .map(foundModel, DeletedModelResponse.class);

        modelRepository.delete(foundModel);
        return deletedModelResponse;
    }
}
