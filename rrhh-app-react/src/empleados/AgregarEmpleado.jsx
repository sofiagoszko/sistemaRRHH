import axios from 'axios'
import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import Swal from 'sweetalert2'

export default function AgregarEmpleado() {

    let navegacion = useNavigate();

    const[empleado, setEmpleado]=useState({
        nombre:"",
        departamento:"",
        salario:""
    })
        
    const{nombre, departamento, salario}=empleado

    const onInputChange = (e)=>{
        setEmpleado({...empleado, [e.target.name]: e.target.value})
    }

    const onSubmit = async(e)=>{
        e.preventDefault()
        const urlBase = "http://localhost:8080/rrhh/empleados"

        try {
            await axios.post(urlBase, empleado);
            Swal.fire({
                icon: "success",
                title: "Éxito",
                text: "¡Empleado agregado con éxito!",
            }).then(() => {
                navegacion("/");
            });
        } catch (error) {
            console.error("Error al agregar empleado:", error);
            Swal.fire({
                icon: "error",
                title: "Error",
                text: "Hubo un error al agregar el empleado",
            });
        }
    }


    return (
        <div className="container">
            <div className="container text-center" style={{margin: "30px"}}>
                <h3>Agregar Empleado</h3>
            </div>
            <form onSubmit={(e)=>onSubmit(e)}>
                <div className="mb-3">
                    <label htmlFor="nombre" className="form-label">Nombre</label>
                    <input type="text" className="form-control" id="nombre" name="nombre" required={true} value={nombre} onChange={(e)=>onInputChange(e)}/>
                </div>
                <div className="mb-3">
                    <label htmlFor="departamento" className="form-label">Departamento</label>
                    <input type="text" className="form-control" id="departamento" name="departamento" required={true} value={departamento} onChange={(e)=>onInputChange(e)}/>
                </div>
                <div className="mb-3">
                    <label htmlFor="salario" className="form-label">Salario</label>
                    <input type="number" step="any" className="form-control" id="salario" name="salario" required={true} value={salario} onChange={(e)=>onInputChange(e)}/>
                </div>
                <div className="text-center">
                    <button type="submit" className="btn btn-warning btn-sm me-3">Agregar</button>
                    <a href="/" className="btn btn-danger btn-sm">Cancelar</a>
                </div>
            </form>
        </div>  
  )
}
