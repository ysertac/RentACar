package com.etiya.rentacar.business.concretes;

import com.etiya.rentacar.business.abstracts.TransmissionService;
import com.etiya.rentacar.business.dtos.requests.TransmissionRequests.CreateTransmissionRequest;
import com.etiya.rentacar.business.dtos.requests.TransmissionRequests.UpdateTransmissionRequest;
import com.etiya.rentacar.business.dtos.responses.TransmissionResponses.*;
import com.etiya.rentacar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentacar.dataAccess.abstracts.TransmissionRepository;
import com.etiya.rentacar.entities.Transmission;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransmissionManager implements TransmissionService {
    private TransmissionRepository transmissionRepository;
    private ModelMapperService modelMapperService;

    @Override
    public GetTransmissionsResponse findById(long id) {
        Optional<Transmission> transmission = transmissionRepository.findById(id);
        GetTransmissionsResponse getTransmissionsResponse = modelMapperService.forResponse()
                .map(transmission.get(),GetTransmissionsResponse.class);
        return getTransmissionsResponse;
    }

    @Override
    public List<GetTransmissionsResponse> findAll() {
        List<Transmission> transmissions = transmissionRepository.findAll();

        List<GetTransmissionsResponse> getTransmissionsResponse = transmissions.stream()
                .map(transmission -> modelMapperService.forResponse()
                        .map(transmission, GetTransmissionsResponse.class)).collect(Collectors.toList());

        return getTransmissionsResponse;
    }

    @Override
    public Transmission findByName(String name) {
        return transmissionRepository.findByName(name).get(0);
    }

    @Override
    public CreatedTransmissionResponse add(CreateTransmissionRequest createTransmissionRequest) {
        Transmission transmission = modelMapperService.forRequest().map(createTransmissionRequest,Transmission.class);
        transmission.setCreatedDate(LocalDateTime.now());
        Transmission createdTransmission =  transmissionRepository.save(transmission);

        CreatedTransmissionResponse createdTransmissionResponse = modelMapperService.forResponse()
                .map(createdTransmission,CreatedTransmissionResponse.class);

        return createdTransmissionResponse;
    }

    @Override
    public UpdatedTransmissionResponse update(UpdateTransmissionRequest updateTransmissionRequest, long id)
            throws Exception {
        Transmission foundTransmission = transmissionRepository.findById(id).orElse(null);
        if (foundTransmission != null){
            modelMapperService.forRequest().map(updateTransmissionRequest, foundTransmission);
            foundTransmission.setUpdatedDate(LocalDateTime.now());
            Transmission updatedTransmission = transmissionRepository.save(foundTransmission);

            return modelMapperService.forResponse().map(updatedTransmission, UpdatedTransmissionResponse.class);
        } else {
            throw new Exception("There is no transmission with this id");
        }
    }

    @Override
    public DeletedTransmissionResponse delete(long id) {
        Transmission foundTransmission = transmissionRepository.findById(id).orElse(null);
        foundTransmission.setDeletedDate(LocalDateTime.now());

        DeletedTransmissionResponse deletedTransmissionResponse = modelMapperService.forResponse()
                .map(foundTransmission, DeletedTransmissionResponse.class);

        transmissionRepository.delete(foundTransmission);
        return deletedTransmissionResponse;
    }
}
