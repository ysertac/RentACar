package com.etiya.rentacar.business.rules;

import com.etiya.rentacar.dataAccess.abstracts.TransmissionRepository;
import com.etiya.rentacar.entities.Transmission;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TransmissionBusinessRules {
    private TransmissionRepository transmissionRepository;

    public void transmissionNotFound(long transmissionId) {
        Optional<Transmission> foundTransmission = transmissionRepository.findById(transmissionId);

        if (!foundTransmission.isPresent()) {
            throw new RuntimeException("There is no transmission with this id");
        }
    }

    public void transmissionNameCannotBeDuplicated(String name){
        Optional<Transmission> transmission = transmissionRepository.findByNameIgnoreCase(name);

        if (transmission.isPresent()){
            throw new RuntimeException("This transmission already exists");
        }
    }

    public void transmissionDeleted(long id) {
        Transmission foundTransmission = transmissionRepository.findById(id).orElse(null);

        if (foundTransmission.getDeletedDate() != null) {
            throw new RuntimeException("This transmission is deleted");
        }
    }
}
