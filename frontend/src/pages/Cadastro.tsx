import React from "react";
import api from "../services/api";
import { useNavigate } from "react-router-dom";
import { useState } from "react";
import SelecionarTipoUser from "../components/SelecionarTipoUser";
import SelecionarLocalizacao from "../components/SelecionarLocalizacao";
import Button from "../components/Button";
import logo from "../assets/logo.png"
import './Auth.css';

const Cadastro: React.FC = () => {
    const navigate = useNavigate();

    const [nome, setNome] = useState("");
    const [email, setEmail] = useState("");
    const [senha, setSenha] = useState("");

    const [nomeError, setNomeError] = useState("");
    const [emailError, setEmailError] = useState("");
    const [senhaError, setSenhaError] = useState("");

    const [tipoUsuario, setTipoUsuario] = useState("");
    const [localizacao, setLocalizacao] = useState("");



    const handleCadastro = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();

        setNomeError("");
        setEmailError("");
        setSenhaError("");

        if (!nome) {
            setNomeError("O campo nome é obrigatório");
            return;
        }

        if (!email) {
            setEmailError("O campo email é obrigatório");
            return;
        } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
            setEmailError("Digite um email válido.");
        }

        if (!senha) {
            setSenhaError("O campo senha é obrigatório");
            return;
        }
        //Se houver algum erro de validação, interrompe o cadastro
        if (nomeError || emailError || senhaError) {
            return;
        }


        try {
            const novoUsuario = {
                nome,
                email,
                senha,
                papel: tipoUsuario,
                endereco: localizacao,
            };

            await api.post("/cadastro/formulario", novoUsuario);
            navigate("/login");
        } catch (error) {
            console.error(error);
        }
    };
    return (
        <div className="auth-container">
            <img src={logo} alt="Logo" className=".logo-login" />
            <h1>Cadastre-se e comece a conectar!</h1>
            <form onSubmit={handleCadastro} className="auth-form">
                <input
                    type="text"
                    placeholder="Nome"
                    value={nome}
                    onChange={event => setNome
                        (event.target.value)}
                />
                {nomeError && <p style={{ color: 'red' }}>{nomeError}</p>} {/* Exibe a mensagem de erro do Nome se existir */}
                <input
                    type="email"
                    placeholder="E-mail"
                    value={email}
                    onChange={event => setEmail
                        (event.target.value)}
                />
                {emailError && <p style={{ color: 'red' }}>{emailError}</p>} {/* Exibe a mensagem de erro do email se existir */}
                <SelecionarTipoUser
                    value={tipoUsuario}
                    onChange={event => setTipoUsuario(event.target.value)}
                />
                <SelecionarLocalizacao
                    value={localizacao}
                    onChange={event => setLocalizacao(event.target.value)}
                />
                <input
                    type="password"
                    placeholder="Senha"
                    value={senha}
                    onChange={event => setSenha(event.target.value)}
                />
                {senhaError && <p style={{ color: 'red' }}>{senhaError}</p>} {/* Exibe a mensagem de erro da senha se existir */}
                <Button type="submit" onClick={() => { }}>Cadastrar</Button>
            </form>
            <p>Já possui cadastro? <a href="/login">Faça login</a></p>

        </div>
    );
};

export default Cadastro;
