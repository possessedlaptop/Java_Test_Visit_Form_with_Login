package com.ep4test2.demo.service;

import com.ep4test2.demo.entity.Visit;
import com.ep4test2.demo.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitService {

    @Autowired
    private VisitRepository visitRepository;

    // usaremos estos para el READ en el controller
    public List<Visit> getAllVisits() {
        return visitRepository.findAll();
    }

    public Visit getVisitById(Long id) {
        return visitRepository.findById(id).orElse(null);
    }

    // parecido a user, guarda un registro en la bd a base de Visit
    public void createVisit(Visit visit) {
        visitRepository.save(visit);
    }

    // para el update en teoria usamos el mismo comando, pero para evitar problemas de lectura usaremos otro metodo
    public void updateVisit(Visit visit) {
        visitRepository.save(visit);
    }

    // para el DELETE en el controller
    public void deleteVisit(Long id) {
        visitRepository.deleteById(id);
    }

    // busca en los registros de la bd y si encuentra un resultado manda TRUE para validar
    public boolean isDniAfiliadoExists(String dniAfiliado) {
        return visitRepository.findByDniAfiliado(dniAfiliado) != null;
    }

    // parecido al anterior, pero en UPDATE necesitamos validar la id del registro a cambiar es la misma tambien
    public boolean isDniAfiliadoExistsForUpdate(String dniAfiliado, Long id) {
        Visit visit = visitRepository.findByDniAfiliado(dniAfiliado);
        return visit != null && !visit.getId().equals(id);
    }
}

