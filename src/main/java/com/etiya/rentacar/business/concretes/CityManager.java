package com.etiya.rentacar.business.concretes;

import com.etiya.rentacar.business.abstracts.CityService;
import com.etiya.rentacar.business.dtos.requests.cityRequests.CreateCityRequest;
import com.etiya.rentacar.business.dtos.requests.cityRequests.UpdateCityRequest;
import com.etiya.rentacar.business.dtos.responses.cityResponse.CreatedCityResponse;
import com.etiya.rentacar.business.dtos.responses.cityResponse.DeletedCityResponse;
import com.etiya.rentacar.business.dtos.responses.cityResponse.GetCityResponse;
import com.etiya.rentacar.business.dtos.responses.cityResponse.UpdatedCityResponse;
import com.etiya.rentacar.business.rules.CityBusinessRules;
import com.etiya.rentacar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentacar.dataAccess.abstracts.CityRepository;
import com.etiya.rentacar.entities.City;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CityManager implements CityService {
    private CityRepository cityRepository;
    private ModelMapperService modelMapperService;
    private CityBusinessRules cityBusinessRules;

    @Override
    public CreatedCityResponse add(CreateCityRequest createCityRequest) {
        cityBusinessRules.cityNameCannotBeDuplicated(createCityRequest.getName());

        City city = modelMapperService.forRequest().map(createCityRequest, City.class);
        City createdCity = cityRepository.save(city);

        CreatedCityResponse createdCityResponse = modelMapperService.forResponse()
                .map(createdCity, CreatedCityResponse.class);

        return createdCityResponse;
    }

    @Override
    public List<GetCityResponse> findAll() {
        List<City> cities = cityRepository.findAll();
        List<GetCityResponse> getCityResponses = cities.stream().filter(city ->
                        city.getDeletedDate() == null)
                .map(city -> modelMapperService.forResponse().map(city, GetCityResponse.class)).collect(Collectors.toList());

        return getCityResponses;
    }

    @Override
    public GetCityResponse findById(long id) {
        cityBusinessRules.cityNotFound(id);
        cityBusinessRules.deletedCity(id);

        City foundCity = cityRepository.findById(id).orElse(null);
        GetCityResponse getCityResponse = modelMapperService.forResponse().map(foundCity, GetCityResponse.class);

        return getCityResponse;
    }

    @Override
    public UpdatedCityResponse update(long id, UpdateCityRequest updateCityRequest) {
        cityBusinessRules.cityNameCannotBeDuplicated(updateCityRequest.getName());
        cityBusinessRules.cityNotFound(id);
        cityBusinessRules.deletedCity(id);

        City foundCity = cityRepository.findById(id).orElse(null);
        City city = modelMapperService.forRequest().map(updateCityRequest, City.class);
        city.setId(id);
        city.setCreatedDate(foundCity.getCreatedDate());
        city.setUpdatedDate(LocalDateTime.now());
        City updatedCity = cityRepository.save(city);

        UpdatedCityResponse updatedCityResponse = modelMapperService.forResponse()
                .map(updatedCity, UpdatedCityResponse.class);

        return updatedCityResponse;
    }

    public DeletedCityResponse delete(long id) {
        cityBusinessRules.cityNotFound(id);
        cityBusinessRules.deletedCity(id);

        City foundCity = cityRepository.findById(id).orElse(null);
        foundCity.setId(id);
        foundCity.setDeletedDate(LocalDateTime.now());
        City deletedCity = cityRepository.save(foundCity);
        DeletedCityResponse deletedCityResponse = modelMapperService
                .forResponse().map(deletedCity, DeletedCityResponse.class);

        return deletedCityResponse;
    }
}
