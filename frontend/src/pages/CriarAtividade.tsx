import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import api from '../services/api';

interface Atividade {
  nome: string;
  descricao: string;
  tags: string[];
  localizacao: string;
  modo: string;
  data: string;
  idosoId: number;
}

const CriarAtividade: React.FC = () => {
  const navigate = useNavigate();

  const [atividade, setAtividade] = useState<Atividade>({
    nome: '',
    descricao: '',
    tags: [],
    localizacao: '',
    modo: '',
    data: '',
    idosoId: 0,
  });

  // const [idosoLogadoId, setIdosoLogadoId] = useState<number | null>(null);

  /*useEffect(() => {
    // Lógica para obter o ID do idoso logado (exemplo com localStorage)
    const storedIdosoId = localStorage.getItem('idosoId');
    if (storedIdosoId) {
      setIdosoLogadoId(parseInt(storedIdosoId, 10));
      setAtividade(prevAtividade => ({
        ...prevAtividade,
        idosoId: parseInt(storedIdosoId, 10),
      }));
    } else {
      //se não estiver logado, volta para a página de login
      navigate('/login');
    }

  }, [navigate]); */
  //atualiza o estado da atividade
  const handleChange = (event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement | HTMLSelectElement>) => {
    const { name, value } = event.target;
    setAtividade({ ...atividade, [name]: value });
  };
  //tratamento das tags(converter em array de strings)
  const handleTagChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const { value } = event.target;
    const tagsArray = value.split(',').map(tag => tag.trim());
    setAtividade({ ...atividade, tags: tagsArray });
  };


  //envia a atividade para o backend e converte a data
  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    try {
      const response = await api.post('/atividades', {
        ...atividade,
        data: atividade.data ? new Date(atividade.data) : null
      });


      console.log('Atividade criada:', response.data);

    navigate('/busca');
    } catch (error) {
      console.error('Erro ao criar atividade:', error);
    }
  };

  // if (!idosoLogadoId) {
  //  return <div>Carregando...</div>;
  // }

  return (
    <div>
      <h1>Criar Conexão</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="nome">Título para a conexão:</label>
          <input type="text" id="nome" name="nome" value={atividade.nome} onChange={handleChange} required />
        </div>
        <div>
          <label htmlFor="descric  ao">Descrição (insira uma forma de contato):</label>
          <textarea id="descricao" name="descricao" value={atividade.descricao} onChange={handleChange} />
        </div>
        <div>
          <label htmlFor="tags">Interesses (separados por vírgula):</label>
          <input type="text" id="tags" name="tags" value={atividade.tags.join(', ')} onChange={handleTagChange} />
        </div>
        <div>
          <label htmlFor="localizacao">Localização da conexão:</label>
          <input type="text" id="localizacao" name="localizacao" value={atividade.localizacao} onChange={handleChange} required />
        </div>
        <div>
          <label htmlFor="modo">Tipo de encontro:</label>
          <input type="text" id="modo" name="modo" value={atividade.modo} onChange={handleChange} required />
        </div>
        <div>
          <label htmlFor="data">Data da conexão:</label>
          <input type="date" id="data" name="data" value={atividade.data} onChange={handleChange} />
        </div>

        <button type="submit">Criar</button>
      </form>
    </div>
  );
};

export default CriarAtividade;