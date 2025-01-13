package com.rrhh.controlador;

import com.rrhh.modelo.Empleado;
import com.rrhh.servicio.IEmpleadoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
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

    @PostMapping("/empleados")
    public ResponseEntity<Empleado> addEmpleado(@RequestBody Empleado empleado){
        logger.info("Empleado a agregar: " + empleado.toString());
        empleadoServicio.guardarEmpleado(empleado);
        return ResponseEntity.ok(empleado);
    }


    @PatchMapping("/empleados/{id}")
    public ResponseEntity<Empleado> updateEmpleado(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {

        Empleado empleado = empleadoServicio.buscarEmpleadoPorId(id);
        if (empleado == null) {
            return ResponseEntity.notFound().build();
        }

        updates.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Empleado.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, empleado, value);
            }
        });

        Empleado empleadoActualizado = empleadoServicio.guardarEmpleado(empleado);

        return ResponseEntity.ok(empleadoActualizado);
    }


}
