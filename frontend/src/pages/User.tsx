import React, { useEffect, useState } from "react";
import api from "../services/api";
import Button from "../components/Button";
import "./User.css";
import { Link } from "react-router-dom";
import logo from "../assets/logo.png";

type User = {
  id: number;
  name: string;
  email: string;
  endereco: string;
  papel: string
}

const UserPage: React.FC = () => {
  const [user, setUser] = useState<User | null>(null);
  const [isEditing, setIsEditing] = useState(false);
  const [endereco, setEndereco] = useState("");
  const [papel, setPapel] = useState("");

  useEffect(() => {
    const userId = localStorage.getItem("userId");
    if (userId) {
      api.get<User>(`/pessoas/${parseInt(userId)}`).then((response) => {
          setUser(response.data);
          setEndereco(response.data.endereco);
          setPapel(response.data.papel);
        })
        .catch((error) => console.error("Erro ao buscar o usuário", error));
    }
  }, []);

  const handleEdit = () => {
    if (user) {
      api.put<User>(`/pessoas/${user.id}`, {
        id: user.id,
        endereco,
        papel,
      })
        .then((response) => {
          setUser(response.data);
          setIsEditing(false);
        })
        .catch((error) => console.error("Erro ao editar perfil", error));
    }
  };


  return (
    <div className="page-container">
      <nav className="navbar">
        <Link to="/">
          <img src={logo} alt="Logo" className="logo" />
        </Link>
        <Button
          variant="reset"
          type="button"
          onClick={() => {
            localStorage.removeItem("userId");
            window.location.href = "/login";
          }}
        >
          Sair
        </Button>
      </nav>
      <div className="informacoes-container">
        <h1>Seu perfil</h1>
        {user ? (
          isEditing ? (
            <div className="edit-form">
              <label>
                Localização:
                <input
                  type="text"
                  value={endereco}
                  onChange={(e) => setEndereco(e.target.value)}
                />
              </label>
              <label>
                Papel:
                <select
                  value={papel}
                  onChange={(e) => setPapel(e.target.value)}
                >
                  <option value="Voluntário">Quero Ajudar</option>
                  <option value="Idoso">Quero Receber ajuda</option>
                </select>
              </label>
              <Button type="button" onClick={handleEdit}>
                Salvar
              </Button>
              <Button type="button" onClick={() => setIsEditing(false)}>
                Cancelar
              </Button>
            </div>
          ) : (
            <div>
              <p>
                <strong>Email:</strong> {user.email}
              </p>
              <p>
                <strong>Localização:</strong> {user.endereco}
              </p>
              <p>
                <strong>Você:</strong> {user.papel}
              </p>
              <Button type="button" onClick={() => setIsEditing(true)}>
                Editar Perfil
              </Button>
              <p>
                <a href="/busca">Voltar</a>
              </p>
            </div>
          )
        ) : (
          <p>Carregando informações do usuário...</p>
        )}
      </div>
    </div>
  );
}
export default UserPage;