import {Routes, Route, BrowserRouter, Navigate } from 'react-router'
import './App.css'
import About from './About.tsx';

function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route index element={<Navigate replace to="about"/>} />
          <Route path='about' element={<About />} />
        </Routes>
      </BrowserRouter>
    </>
  )
}

export default App
