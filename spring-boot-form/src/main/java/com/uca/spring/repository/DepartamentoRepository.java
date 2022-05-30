package com.uca.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.uca.spring.model.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {

}
