import React, { useState } from 'react';
import SelecionarLocalizacao from "../components/SelecionarLocalizacao";
import ListaDeAtividades from '../components/ListaAtividades';
import SelecionarTags from '../components/SelecionarTags';
import SelecionarTipoEncontro from '../components/SelecionarTipoEncontro';
import Button from "../components/Button";
import './Atividades.css';  // Importe o novo CSS
import './Navbar.css' //importe o css do navbar
import { Link } from 'react-router-dom';
import logo from '../assets/logo.png'; // Importe o logo
import icon from "../assets/iconuser.svg"

const Atividades: React.FC = () => {

    const [tag, setTag] = useState("");
    const [tipoEncontro, setTipoEncontro] = useState("");
    const [localizacao, setLocalizacao] = useState("");
    const [filtros, setFiltros] = useState({
        modo: "",
        localizacao: "",
        tag: "",
    }
    );
    //h minusculo porque é uma função
    const handleAtividades = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        setFiltros({ modo: tipoEncontro, localizacao: localizacao, tag: tag });
    }

    const handleResetForm = () => {
        setFiltros({ modo: "", localizacao: "", tag: "" });
        setTag("");
        setTipoEncontro("");
        setLocalizacao("");
    }
    return (
        <div className="page-container">
            <nav className="navbar">
                <Link to="/"><img src={logo} alt="Logo" className="logo" /></Link> {/* Substitua pelo seu logo */}
                <Link to="/User"><img src={icon} className="fas fa-user profile-icon" /></Link> {/* Ícone de perfil (FontAwesome) */}
            </nav>
            <div className="atividades-container">
                <Button variant="create" type="button" onClick={() => { window.location.href = '/criar'; }} >Criar uma conexão</Button>
                <form onSubmit={handleAtividades} onReset={handleResetForm}>
                    <h2>Encontre uma conexão</h2>
                    <div className="filtros-container">
                        <SelecionarTags
                            value={tag}
                            onChange={event => setTag(event.target.value)}
                        />
                        &emsp;
                        <SelecionarTipoEncontro
                            value={tipoEncontro}
                            onChange={event => setTipoEncontro(event.target.value)}
                        />
                        &emsp;
                        <SelecionarLocalizacao
                            value={localizacao}
                            onChange={event => setLocalizacao(event.target.value)}
                        />
                    </div>
                    <div style={{ display: 'flex', justifyContent: 'center' }}>
                        <Button variant="search" type="submit" onClick={() => { }}>Buscar</Button>
                        &emsp;
                        <Button variant="reset" type="reset" onClick={() => { }}>Limpar</Button>
                    </div>

                    <ListaDeAtividades filtros={filtros} />
                </form>

            </div>
        </div>
    );
};

export default Atividades;


















