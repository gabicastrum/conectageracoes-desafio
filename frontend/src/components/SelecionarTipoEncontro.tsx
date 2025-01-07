import React from "react";

interface SelecionarTipoEncontroProps {
    onChange: (event: React.ChangeEvent<HTMLSelectElement>) => void;
    value: string;
}

const SelecionarTipoEncontro: React.FC<SelecionarTipoEncontroProps> = ({ onChange, value }) => {
    const encontros = [
        { id: "", tipo: "Selecione um tipo de encontro" },
        { id: "Presencial", tipo: "Presencial" },
        { id: "Remoto", tipo: "Remoto" }

    ];
    return (
        <div>
            <select value={value} onChange={onChange}>
                {encontros.map(encontro => (
                    <option key={encontro.id} value={encontro.id}>
                        {encontro.tipo}
                    </option>
                ))}
            </select>
        </div>
    );
};

export default SelecionarTipoEncontro;