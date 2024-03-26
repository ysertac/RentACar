package com.etiya.rentacar.business.rules;

import com.etiya.rentacar.dataAccess.abstracts.ModelRepository;
import com.etiya.rentacar.entities.Model;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ModelBusinessRules {
    private ModelRepository modelRepository;

    public void ModelNameCannotBeDuplicated(String modelName) {
        Optional<Model> foundModel = modelRepository.findByNameIgnoreCase(modelName);

        if (foundModel.isPresent()) {
            throw new RuntimeException("This model already exists");
        }
    }

    public void ModelNotFound(long id) {
        Optional<Model> foundModel = modelRepository.findById(id);

        if (!foundModel.isPresent()) {
            throw new RuntimeException("There is no model with this id");
        }
    }
}