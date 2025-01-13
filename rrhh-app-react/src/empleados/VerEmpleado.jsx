import axios from 'axios'
import React, { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'


export default function EditarEmpleado() {

    const urlBase = "http://localhost:8080/rrhh/empleados"


    const {id} =useParams();

    const[empleado, setEmpleado]=useState({
        nombre:"",
        departamento:"",
        salario:""
    })
        
    const{nombre, departamento, salario}=empleado

    useEffect(()=>{
        cargarEmpleado()
    }, []) 

    const cargarEmpleado = async () =>{
        const resultado = await axios.get(`${urlBase}/${id}`)
        setEmpleado(resultado.data)
    }



    return (
        <div className="container">
            <div className="container text-center">
                <h3>Información del Empleado</h3>
            </div>
            <form>
                <div className="mb-3">
                    <label htmlFor="nombre" className="form-label">Nombre</label>
                    <input type="text" className="form-control" id="nombre" name="nombre" value={nombre} disabled={true}/>
                </div>
                <div className="mb-3">
                    <label htmlFor="departamento" className="form-label">Departamento</label>
                    <input type="text" className="form-control" id="departamento" name="departamento" value={departamento} disabled={true}/>
                </div>
                <div className="mb-3">
                    <label htmlFor="salario" className="form-label">Salario</label>
                    <input type="number" step="any" className="form-control" id="salario" name="salario" value={salario} disabled={true}/>
                </div>
                <div className="text-center">
                    <a href="/" className="btn btn-success btn-sm">Volver</a>
                </div>
            </form>
        </div>  
  )
}
