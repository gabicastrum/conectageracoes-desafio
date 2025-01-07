import { render, screen, fireEvent } from '@testing-library/react';
import SelecionarTipoUser from '../SelecionarTipoUser';

describe('Componente SelecionarTipoUser', () => {
    it('renderiza o select com as opções corretas', () => {
        render(<SelecionarTipoUser onChange={() => { }} value="" />);
        expect(screen.getByRole('combobox')).toBeInTheDocument();
        expect(screen.getByRole('option', { name: /Selecione uma das opções/i })).toBeInTheDocument();
        expect(screen.getByRole('option', { name: /Quero ajudar/i })).toBeInTheDocument();
        expect(screen.getByRole('option', { name: /Quero receber ajuda/i })).toBeInTheDocument();
    });

    it('chama a função onChange ao selecionar uma opção', () => {
        const onChangeMock = jest.fn();
        render(<SelecionarTipoUser onChange={onChangeMock} value="" />);

        fireEvent.change(screen.getByRole('combobox'), { target: { value: '2' } }); // Seleciona "Quero ajudar"

        expect(onChangeMock).toHaveBeenCalledTimes(1);
        expect(onChangeMock).toHaveBeenCalledWith(expect.any(Object)); // Verifica se o evento é passado
    });

    it('exibe o valor inicial correto', () => {
        render(<SelecionarTipoUser onChange={() => { }} value="3" />); // Define "Quero receber ajuda" como valor inicial

        expect(screen.getByRole('combobox')).toHaveValue('3');
    });

    it('valor padrão está selecionado no início', () => {
        render(<SelecionarTipoUser onChange={() => { }} value="" />);

        const selectElement = screen.getByRole('combobox') as HTMLSelectElement;
        expect(selectElement.value).toBe(""); // Verifica se o valor padrão "" está selecionado
    });
});