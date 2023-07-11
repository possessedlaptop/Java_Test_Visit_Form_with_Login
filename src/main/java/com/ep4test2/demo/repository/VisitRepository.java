package com.ep4test2.demo.repository;

import com.ep4test2.demo.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {

    // Consulta con la base de datos el registro guardado de la tabla tbl_visitas por DNI_AFILIADO
    Visit findByDniAfiliado(String dniAfiliado);
}