package com.etiya.rentacar.dataAccess.abstracts;

import com.etiya.rentacar.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModelRepository extends JpaRepository<Model, Long> {
    List<Model> findByName(String name);
}
