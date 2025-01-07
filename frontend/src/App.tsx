import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import Cadastro from "./pages/Cadastro";
import Login from "./pages/Login";
import User from "./pages/User";
import CriarAtividade from "./pages/CriarAtividade";
import Atividades from "./pages/Atividades";


function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Navigate to="/login" />} />
        <Route path="/login" element={<Login />} />
        <Route path="/cadastro" element={<Cadastro />} />
        <Route path="/user" element={<User />} />
        <Route path="/busca" element={<Atividades />} />
        <Route path="/criar" element={<CriarAtividade />} />
      </Routes>

    </BrowserRouter>
  );
}

export default App;