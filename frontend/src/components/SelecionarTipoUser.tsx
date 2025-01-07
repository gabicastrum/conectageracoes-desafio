interface SelecionarTipoUserProps {
    onChange: (event: React.ChangeEvent<HTMLSelectElement>) => void;
    value: string;
}


const SelecionarTipoUser: React.FC<SelecionarTipoUserProps> = ({ onChange, value }) => {
    return (
        <div>
            <select value={value} onChange={onChange}>
                <option value="">Selecione uma das opções</option>
                <option value="Gostaria de ajudar">Gostaria de ajudar</option>
                <option value="Gostaria de receber ajuda">Gostaria de receber ajuda</option>
            </select>
        </div>
    );
};

export default SelecionarTipoUser;