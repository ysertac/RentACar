package com.etiya.rentacar.business.rules;

import com.etiya.rentacar.business.messages.ModelMessages;
import com.etiya.rentacar.core.exceptions.types.BusinessException;
import com.etiya.rentacar.dataAccess.abstracts.ModelRepository;
import com.etiya.rentacar.entities.Model;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ModelBusinessRules {
    private ModelRepository modelRepository;

    public void modelNameCannotBeDuplicated(String modelName) {
        Optional<Model> foundModel = modelRepository.findByNameIgnoreCase(modelName);

        if (foundModel.isPresent() && foundModel.get().getDeletedDate() == null) {
            throw new BusinessException(ModelMessages.modelNameCannotBeDuplicated);
        }
    }

    public void modelNotFound(long id) {
        Optional<Model> foundModel = modelRepository.findById(id);

        if (!foundModel.isPresent()) {
            throw new BusinessException(ModelMessages.modelNotFound);
        }
    }

    public void deletedModel(long id) {
        Model foundModel = modelRepository.findById(id).orElse(null);

        if (foundModel.getDeletedDate() != null) {
            throw new BusinessException(ModelMessages.deletedModel);
        }
    }
}
