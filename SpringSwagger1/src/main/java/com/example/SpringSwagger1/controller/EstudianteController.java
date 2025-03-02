package com.example.SpringSwagger1.controller;

import com.example.SpringSwagger1.model.Estudiante;
import com.example.SpringSwagger1.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EstudianteController {

    @Autowired
    public EstudianteService estudianteService;

    @GetMapping("/listar")
    public List<Estudiante> listarEstudiante() {
        return estudianteService.listar();
    }

    @PostMapping("/guardar")
    public Estudiante guardarEstudiane(@RequestBody Estudiante estudiante) {
        return estudianteService.guardar(estudiante);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Estudiante> listarPorId(@PathVariable int id) {
        return estudianteService.buscarporId(id)
                .map(ResponseEntity::ok) // Si el estudiante existe, devuelve 200 OK con el estudiante
                .orElseGet(() -> ResponseEntity.notFound().build()); // Si no existe, devuelve 404 Not Found
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarEstudiante(@PathVariable int id){
        estudianteService.eliminar(id);
    }

    @GetMapping("/telefono/{telefono}")
    public ResponseEntity<Estudiante> buscarPorTelefono(@PathVariable String telefono) {
        Optional<Estudiante> estudiante = estudianteService.buscarporTelefono(telefono);

        if (estudiante.isPresent()) {
            return ResponseEntity.ok(estudiante.get()); // Devuelve el estudiante con estado 200 OK
        } else {
            return ResponseEntity.notFound().build(); // Devuelve 404 Not Found si no existe
        }
    }



}
