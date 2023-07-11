package com.ep4test2.demo.controller;

import com.ep4test2.demo.entity.Visit;
import com.ep4test2.demo.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/visitas")
public class VisitController {

    @Autowired
    private VisitService visitService;

    // READ: por defecto nos muestra las visitas guardadas en la db tbl_visitas
    @GetMapping("")
    public String showVisits(Model model) {
        List<Visit> visits = visitService.getAllVisits();
        model.addAttribute("visits", visits);
        return "visitas";
    }

    // cuando presionamos el boton Add Visit nos manda a otro html armado a base la entidad Visit
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("visit", new Visit());
        return "visit-form";
    }

    // CREATE: nos deja crear una visita en la bd
    @PostMapping("")
    public String createVisit(@ModelAttribute("visit") Visit visit, Model model) {
        // Validacion: si ya existe el usuario en el campo DNI_AFILIADO nos manda a la pagina visitas con un error
        if (visitService.isDniAfiliadoExists(visit.getDniAfiliado())) {
            model.addAttribute("errorMessage", "DNI Afiliado already exists");
            List<Visit> visits = visitService.getAllVisits();
            model.addAttribute("visits", visits);
            return "visitas";
        }
        // si esta disponible el dni se procede a crear el registro
        //TODO: validar solo se reciban datos validos para varchar(8)
        visitService.createVisit(visit);
        return "redirect:/visitas";
    }


    // Si presionamos el boton edit nos muestra una forma en html a base de la entity Visit y mandamoos el numero de Id del registro
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Visit visit = visitService.getVisitById(id);
        model.addAttribute("visit", visit);
        return "visit-form";
    }

    // UPDATE: en la forma anterior, hacemos la validacion necesaria
    @PostMapping("/{id}")
    public String updateVisit(@PathVariable("id") Long id, @ModelAttribute("visit") Visit visit, Model model) {
        // Validacion: si el dni ya existe en otro registro de la bd, nos manda un error qye se visualiza en la forma
        if (visitService.isDniAfiliadoExistsForUpdate(visit.getDniAfiliado(), id)) {
            model.addAttribute("errorMessage", "DNI Afiliado already exists");
            return "visit-form";
        }
        // si pasa la validacion se procede a editra el registro a base del id recibido
        visit.setId(id);
        visitService.updateVisit(visit);
        return "redirect:/visitas";
    }

    // DELETE: parecido al anterior, recibimos el id y con este mandamos a borrar el registro, luego volvemos a la lista de registros para que se compruebe
    @GetMapping("/{id}/delete")
    public String deleteVisit(@PathVariable("id") Long id) {
        visitService.deleteVisit(id);
        return "redirect:/visitas";
    }
}

