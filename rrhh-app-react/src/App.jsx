import { BrowserRouter, Route, Routes } from 'react-router-dom'
import ListadoEmpleados from './empleados/ListadoEmpleados'
import Navegacion from './plantilla/Navegacion'
import AgregarEmpleado from './empleados/AgregarEmpleado'
import EditarEmpleado from './empleados/EditarEmpleado'
import VerEmpleado from './empleados/VerEmpleado'


//import reactLogo from './assets/react.svg'
//import viteLogo from '/vite.svg'
//import './App.css'

function App() {
  return (
    <div className="container">
      <BrowserRouter>
        <Navegacion/>
        <Routes>
          <Route exact path="/" element={<ListadoEmpleados/>}/>
          <Route exact  path="/agregar" element={<AgregarEmpleado/>}/>
          <Route exact  path="/ver/:id" element={<VerEmpleado/>}/>
          <Route exact  path="/editar/:id" element={<EditarEmpleado/>}/>
        </Routes>
      </BrowserRouter>
    </div>

  )
}


export default App
