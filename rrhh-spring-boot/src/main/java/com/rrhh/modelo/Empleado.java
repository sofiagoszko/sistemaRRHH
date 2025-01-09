package com.rrhh.modelo;


import lombok.Data;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Data
@Table(name = "empleados")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idEmpleado;

    @Column(name = "nombre")
    String nombre;
    @Column(name = "departamento")
    String departamento;
    @Column(name = "salario")
    Double salario;

    public Empleado() {}

    public Empleado(String nombre, String departamento, Double salario){
        this.nombre = nombre;
        this.departamento = departamento;
        this.salario = salario;
    }

    public String toString(){
        return "Nombre: "+nombre+" Departamento: "+departamento+" Salario: "+salario;
    }
}

