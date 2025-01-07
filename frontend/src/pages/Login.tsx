import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../services/api";
import "./Auth.css";
import logo from "../assets/logo.png"


const Login: React.FC = () => {
    const navigate = useNavigate();
    const [email, setEmail] = useState("");
    const [senha, setSenha] = useState("");
   const [error, setError] = useState("");

    //função assincrona para lidar com o login
    const handleLogin = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();   
        setError("");

        //Validação do email
        if (!email) {
            setError("O campo email é obrigatório");
            return;
        } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
            setError("Digite um email válido.");
        }

        if (!senha) {
            setError("O campo senha é obrigatório");
            return;
        }

        try {
            const response = await api.post<string>("/login", { email, senha });
            console.log("Login efetuado com sucesso:", response); 
            console.log("ID do usuário logado:", response.data);
            localStorage.setItem("userId", response.data);
            navigate("/busca");

        } catch (err: any) { 
            console.error("Erro ao fazer login:", err);
            if (err.response && err.response.data && err.response.data.message) {
                setError(err.response.data.message);
            } else {
                setError("Erro ao fazer login. Verifique suas credenciais e tente novamente.");
            }
        }
    };

    return (
        <div className="auth-container">
            <img src={logo} alt="Logo" className=".logo-login" />
            <h1> Entre e reconecte-se</h1>
            <form onSubmit={handleLogin} className="auth-form"> 
                <input
                    type="email"
                    placeholder="E-mail"
                    value={email}     /* Valor do input vinculado ao estado 'email'. */
                    onChange={(e) => setEmail(e.target.value)} /* Atualiza o estado 'email' quando o input muda. */
                />
                {error && <p style={{ color: "red" }}>{error}</p>} {/* Exibe a mensagem de erro do email */}
                <input
                    type="password"
                    placeholder="Senha"
                    value={senha}  /* Valor do input vinculado ao estado 'senha'. */
                    onChange={(e) => setSenha(e.target.value)} /* Atualiza o estado 'senha' quando o input muda. */
                />
                {error && <p style={{ color: "red" }}>{error}</p>} {/* Exibe a mensagem de erro da senha */}
                <button type="submit">Entrar</button>
            </form>
            <p>Não possui cadastro? <a href="/cadastro">Cadastre-se</a></p>
        </div>
    );
};

export default Login;