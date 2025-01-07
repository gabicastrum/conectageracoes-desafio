import { useState, useEffect } from 'react';
import api from "../services/api";

interface Atividade {
    id: number;
    nome: string;
    descricao: string;
    tags: string[];
    localizacao: string;
    modo: string;
    data: string;
    idoso: Idoso;
    voluntario?: any;
    status: string;
    necessidades: string[];
}

interface Idoso {
    id: number;
    nome: string;
    email: string;
    senha: string;
    endereco: string;
    papel: string;
}

function ListaDeAtividades({ filtros }: {
    filtros: {
        modo: string;
        localizacao: string;
        tag: string;
    }
}) {
    // Estado para armazenar a lista de Atividades
    const [atividades, setAtividades] = useState<Atividade[]>([]);
    // Estado para gerenciar o carregamento
    const [carregando, setCarregando] = useState<boolean>(true);
    // Estado para gerenciar erros
    const [erro, setErro] = useState<string | undefined>(undefined);

    // useEffect para fazer a requisição à API assim que o componente for montado
    useEffect(() => {
        const query = criarQuery();
        // Fazendo a requisição GET para o backend usando axios
        api.get(`/busca/atividades?${query}`).then((response) => {
            setAtividades(response.data as Atividade[])  // Armazena os dados recebidos no estado
            setCarregando(false);         // Finaliza o carregamento
        })
            .catch((error) => {
                setErro("Erro ao carregar as Atividades");
                setCarregando(false);         // Finaliza o carregamento, mesmo em erro
            });
    }, [filtros]);  // O array vazio garante que a requisição seja feita apenas uma vez, na montagem do componente

    const criarQuery = () => {
        let query = '';
        if (filtros.modo) {
            query += `modo=${filtros.modo}&`
        }

        if (filtros.localizacao) {
            query += `localizacao=${filtros.localizacao}&`;
        }

        if (filtros.tag) {
            query += `tag=${filtros.tag}`;
        }
        return query;
    }

    // Exibe um indicador de carregamento enquanto os dados não são carregados
    if (carregando) {
        return <div>Carregando...</div>;
    }

    // Exibe uma mensagem de erro caso ocorra um erro
    if (erro) {
        return <div>{erro}</div>;
    }

    return (
        <div>
            <h1>Lista de Conexões</h1>
            <table>
                <thead>
                    <tr>
                        <th>Título</th>
                        <th>Interesse</th>
                        <th>Data</th>
                        <th>Localização</th>
                        <th>Encontro</th>
                        <th>Descrição</th>
                    </tr>
                </thead>
                <tbody>
                    {atividades.map((atividade, index) => (
                        <tr key={index}>
                            <td>{atividade.nome}</td>
                            <td>{atividade.tags?.join(", ")}</td>
                            <td>{atividade.data}</td>
                            <td>{atividade.localizacao}</td>
                            <td>{atividade.modo}</td>
                            <td>{atividade.descricao}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default ListaDeAtividades;