package com.etiya.rentacar.business.concretes;

import com.etiya.rentacar.business.abstracts.TransmissionService;
import com.etiya.rentacar.business.dtos.requests.TransmissionRequests.CreateTransmissionRequest;
import com.etiya.rentacar.business.dtos.requests.TransmissionRequests.UpdateTransmissionRequest;
import com.etiya.rentacar.business.dtos.responses.TransmissionResponses.*;
import com.etiya.rentacar.dataAccess.abstracts.TransmissionRepository;
import com.etiya.rentacar.entities.Transmission;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransmissionManager implements TransmissionService {
    private TransmissionRepository transmissionRepository;

    public TransmissionManager(TransmissionRepository transmissionRepository) {
        this.transmissionRepository = transmissionRepository;
    }

    @Override
    public GetTransmissionResponse findById(long id) {
        Optional<Transmission> foundTransmission = transmissionRepository.findById(id);
        GetTransmissionResponse getTransmissionResponse = new GetTransmissionResponse();
        getTransmissionResponse.setId(foundTransmission.get().getId());
        getTransmissionResponse.setName(foundTransmission.get().getName());
        getTransmissionResponse.setCreatedDate(foundTransmission.get().getCreatedDate());
        getTransmissionResponse.setUpdatedDate(foundTransmission.get().getUpdatedDate());
        getTransmissionResponse.setDeletedDate(foundTransmission.get().getDeletedDate());

        return getTransmissionResponse;
    }

    @Override
    public GetTransmissionsResponse findAll() {
        List<Transmission> allTransmissions = transmissionRepository.findAll();
        List<String> transmissionNames = new ArrayList<>();
        GetTransmissionsResponse getTransmissionsResponse = new GetTransmissionsResponse();
        for (Transmission t : allTransmissions) {
            transmissionNames.add(t.getName());
        }
        getTransmissionsResponse.setTransmissions(transmissionNames);
        return getTransmissionsResponse;
    }

    @Override
    public Transmission findByName(String name) {
        return transmissionRepository.findByName(name).get(0);
    }

    @Override
    public CreatedTransmissionResponse add(CreateTransmissionRequest createTransmissionRequest) {
        Transmission transmission = new Transmission();
        transmission.setName(createTransmissionRequest.getName());
        transmission.setCreatedDate(LocalDateTime.now());
        Transmission createdTransmission = transmissionRepository.save(transmission);

        CreatedTransmissionResponse createdTransmissionResponse = new CreatedTransmissionResponse();
        createdTransmissionResponse.setId(createdTransmission.getId());
        createdTransmissionResponse.setName(createdTransmission.getName());
        createdTransmissionResponse.setCreatedTime(createdTransmission.getCreatedDate());
        createdTransmissionResponse.setUpdatedTime(createdTransmission.getUpdatedDate());
        createdTransmissionResponse.setDeletedTime(createdTransmission.getDeletedDate());

        return createdTransmissionResponse;
    }

    @Override
    public UpdatedTransmissionResponse update(UpdateTransmissionRequest updateTransmissionRequest, long id) {
        Optional<Transmission> foundTransmission = transmissionRepository.findById(id);
        foundTransmission.get().setId(id);
        foundTransmission.get().setName(updateTransmissionRequest.getName());
        foundTransmission.get().setUpdatedDate(LocalDateTime.now());
        Transmission updatedTransmission = transmissionRepository.save(foundTransmission.get());

        UpdatedTransmissionResponse updatedTransmissionResponse = new UpdatedTransmissionResponse();
        updatedTransmissionResponse.setId(updatedTransmission.getId());
        updatedTransmissionResponse.setName(updatedTransmission.getName());
        updatedTransmissionResponse.setCreatedDate(updatedTransmission.getCreatedDate());
        updatedTransmissionResponse.setUpdatedDate(LocalDateTime.now());
        updatedTransmissionResponse.setDeletedDate(updatedTransmission.getDeletedDate());
        return updatedTransmissionResponse;
    }

    @Override
    public DeletedTransmissionResponse delete(long id) {
        Optional<Transmission> foundTransmission = transmissionRepository.findById(id);
        Transmission deletedTransmission = foundTransmission.get();

        DeletedTransmissionResponse deletedTransmissionResponse = new DeletedTransmissionResponse();
        deletedTransmissionResponse.setId(deletedTransmission.getId());
        deletedTransmissionResponse.setName(deletedTransmission.getName());
        deletedTransmissionResponse.setCreatedDate(deletedTransmission.getCreatedDate());
        deletedTransmissionResponse.setUpdatedDate(deletedTransmission.getUpdatedDate());
        deletedTransmissionResponse.setDeletedDate(LocalDateTime.now());

        transmissionRepository.delete(foundTransmission.get());
        return deletedTransmissionResponse;
    }
}
