import React from "react"

interface SelecionarLocalizacaoProps {
    onChange: (event: React.ChangeEvent<HTMLSelectElement>) => void;
    value: string;
}

const SelecionarLocalizacao: React.FC<SelecionarLocalizacaoProps> = ({ onChange, value }) => {
    const localizacoes = [
        { id: "0", nome: "Selecione uma capital" },
        { id: "Aracaju - SE", nome: "Aracaju - SE" },
        { id: "Belém - PA", nome: "Belém - PA" },
        { id: "Belo Horizonte - MG", nome: "Belo Horizonte - MG" },
        { id: "Boa Vista - RR", nome: "Boa Vista - RR" },
        { id: "Brasília - DF", nome: "Brasília - DF" },
        { id: "Campo Grande - MS", nome: "Campo Grande - MS" },
        { id: "Cuiabá - MT", nome: "Cuiabá - MT" },
        { id: "Curitiba - PR", nome: "Curitiba - PR" },
        { id: "Florianópolis - SC", nome: "Florianópolis - SC" },
        { id: "Fortaleza - CE", nome: "Fortaleza - CE" },
        { id: "Goiânia - GO", nome: "Goiânia - GO" },
        { id: "João Pessoa - PB", nome: "João Pessoa - PB" },
        { id: "Macapá - AP", nome: "Macapá - AP" },
        { id: "Maceió - AL", nome: "Maceió - AL" },
        { id: "Manaus - AM", nome: "Manaus - AM" },
        { id: "Natal - RN", nome: "Natal - RN" },
        { id: "Palmas - TO", nome: "Palmas - TO" },
        { id: "Porto Alegre - RS", nome: "Porto Alegre - RS" },
        { id: "Porto Velho - RO", nome: "Porto Velho - RO" },
        { id: "Recife - PE", nome: "Recife - PE" },
        { id: "Rio Branco - AC", nome: "Rio Branco - AC" },
        { id: "Rio de Janeiro - RJ", nome: "Rio de Janeiro - RJ" },
        { id: "Salvador - BA", nome: "Salvador - BA" },
        { id: "São Luís - MA", nome: "São Luís - MA" },
        { id: "São Paulo - SP", nome: "São Paulo - SP" },
        { id: "Teresina - PI", nome: "Teresina - PI" },
        { id: "Vitória - ES", nome: "Vitória - ES" },
        { id: "28", nome: "------------" }
    ];
    return (
        <div>
            <select value={value} onChange={onChange}>
                {localizacoes.map(localizacao => (
                    <option key={localizacao.id} value={localizacao.id}>
                        {localizacao.nome}
                    </option>
                ))}
            </select>
        </div>
    );
};

export default SelecionarLocalizacao;