package com.etiya.rentacar.business.concretes;

import com.etiya.rentacar.business.abstracts.TransmissionService;
import com.etiya.rentacar.business.dtos.requests.TransmissionRequests.CreateTransmissionRequest;
import com.etiya.rentacar.business.dtos.requests.TransmissionRequests.UpdateTransmissionRequest;
import com.etiya.rentacar.business.dtos.responses.TransmissionResponses.CreatedTransmissionResponse;
import com.etiya.rentacar.business.dtos.responses.TransmissionResponses.DeletedTransmissionResponse;
import com.etiya.rentacar.business.dtos.responses.TransmissionResponses.GetTransmissionsResponse;
import com.etiya.rentacar.business.dtos.responses.TransmissionResponses.UpdatedTransmissionResponse;
import com.etiya.rentacar.business.rules.TransmissionBusinessRules;
import com.etiya.rentacar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentacar.dataAccess.abstracts.TransmissionRepository;
import com.etiya.rentacar.entities.Transmission;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransmissionManager implements TransmissionService {
    private TransmissionRepository transmissionRepository;
    private TransmissionBusinessRules transmissionBusinessRules;
    private ModelMapperService modelMapperService;

    @Override
    public GetTransmissionsResponse findById(long id) {
        transmissionBusinessRules.transmissionNotFound(id);
        transmissionBusinessRules.transmissionDeleted(id);

        Optional<Transmission> transmission = transmissionRepository.findById(id);
        GetTransmissionsResponse getTransmissionsResponse = modelMapperService.forResponse()
                .map(transmission.get(),GetTransmissionsResponse.class);
        return getTransmissionsResponse;
    }

    @Override
    public List<GetTransmissionsResponse> findAll() {
        List<Transmission> transmissions = transmissionRepository.findAll();

        List<GetTransmissionsResponse> getTransmissionsResponse = transmissions.stream()
                .filter(transmission -> transmission.getDeletedDate() == null)
                .map(transmission ->
                        modelMapperService.forResponse().map(transmission, GetTransmissionsResponse.class)).collect(Collectors.toList());

        return getTransmissionsResponse;
    }

    @Override
    public CreatedTransmissionResponse add(CreateTransmissionRequest createTransmissionRequest) {
        transmissionBusinessRules.transmissionNameCannotBeDuplicated(createTransmissionRequest.getName());

        Transmission transmission = modelMapperService.forRequest().map(createTransmissionRequest,Transmission.class);
        transmission.setCreatedDate(LocalDateTime.now());
        Transmission createdTransmission =  transmissionRepository.save(transmission);

        CreatedTransmissionResponse createdTransmissionResponse = modelMapperService.forResponse()
                .map(createdTransmission,CreatedTransmissionResponse.class);

        return createdTransmissionResponse;
    }

    @Override
    public UpdatedTransmissionResponse update(UpdateTransmissionRequest updateTransmissionRequest, long id) {
        transmissionBusinessRules.transmissionNameCannotBeDuplicated(updateTransmissionRequest.getName());
        transmissionBusinessRules.transmissionNotFound(id);
        transmissionBusinessRules.transmissionDeleted(id);

        Transmission foundTransmission = transmissionRepository.findById(id).orElse(null);
        modelMapperService.forRequest().map(updateTransmissionRequest, foundTransmission);
        foundTransmission.setUpdatedDate(LocalDateTime.now());
        Transmission updatedTransmission = transmissionRepository.save(foundTransmission);

        return modelMapperService.forResponse().map(updatedTransmission, UpdatedTransmissionResponse.class);
    }

    @Override
    public DeletedTransmissionResponse delete(long id) {
        transmissionBusinessRules.transmissionNotFound(id);
        transmissionBusinessRules.transmissionDeleted(id);

        Transmission foundTransmission = transmissionRepository.findById(id).orElse(null);
        foundTransmission.setId(id);
        foundTransmission.setDeletedDate(LocalDateTime.now());
        Transmission deletedTransmission = transmissionRepository.save(foundTransmission);

        DeletedTransmissionResponse deletedTransmissionResponse = modelMapperService.forResponse()
                .map(deletedTransmission, DeletedTransmissionResponse.class);

        return deletedTransmissionResponse;
    }
}
