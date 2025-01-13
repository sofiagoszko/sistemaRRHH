package com.rrhh.controlador;

import com.rrhh.excepcion.RecursoNoEncontradoExcepcion;
import com.rrhh.modelo.Empleado;
import com.rrhh.servicio.IEmpleadoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("rrhh")
@CrossOrigin(value = "*")
public class EmpleadoControlador {

    private static final Logger logger = LoggerFactory.getLogger(EmpleadoControlador.class);

    @Autowired
    private IEmpleadoServicio empleadoServicio;

    @GetMapping("/")
    public String index() {
        return "Aplicación de Recursos Humanos";
    }

    @GetMapping("/empleados")
    public List<Empleado> getEmpleados(){
        List<Empleado> empleados = empleadoServicio.listarEmpleados();
        empleados.forEach(empleado -> logger.info(empleado.toString()));
        return empleados;
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> getEmpleadoPorId(@PathVariable Long id){
        Empleado empleado = empleadoServicio.buscarEmpleadoPorId(id);
        if (empleado == null) {
            throw new RecursoNoEncontradoExcepcion("No se encontró el empleado con el id " + id);
        }
        return ResponseEntity.ok(empleado);

    }

    @PostMapping("/empleados")
    public ResponseEntity<Empleado> addEmpleado(@RequestBody Empleado empleado){
        logger.info("Empleado a agregar: " + empleado.toString());
        empleadoServicio.guardarEmpleado(empleado);
        return ResponseEntity.ok(empleado);
    }


    @PatchMapping("/empleados/{id}")
    public ResponseEntity<Empleado> updateEmpleado(
            @PathVariable Long id,
            @RequestBody Empleado empleadoModificado) {

        Empleado empleado = empleadoServicio.buscarEmpleadoPorId(id);
        if (empleado == null) {
            throw new RecursoNoEncontradoExcepcion("No se encontró el empleado con el id " + id);
        }

        if (empleadoModificado.getNombre() != null) {
            empleado.setNombre(empleadoModificado.getNombre());
        }
        if (empleadoModificado.getDepartamento() != null) {
            empleado.setDepartamento(empleadoModificado.getDepartamento());
        }
        if (empleadoModificado.getSalario() != null) {
            empleado.setSalario(empleadoModificado.getSalario());
        }

        Empleado empleadoActualizado = empleadoServicio.guardarEmpleado(empleado);

        return ResponseEntity.ok(empleadoActualizado);
    }

    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmpleado(@PathVariable Long id) {

        Empleado empleado = empleadoServicio.buscarEmpleadoPorId(id);
        if (empleado == null) {
            throw new RecursoNoEncontradoExcepcion("No se encontró el empleado con el id " + id);
        }
        empleadoServicio.eliminarEmpleado(empleado);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }


}
