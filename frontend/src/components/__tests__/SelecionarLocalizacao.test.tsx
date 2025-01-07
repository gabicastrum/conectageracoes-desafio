import { render, screen, fireEvent } from '@testing-library/react';
import SelecionarLocalizacao from '../SelecionarLocalizacao';

describe('Componente SelecionarLocalizacao', () => {
  it('renderiza o select com as opções corretas', () => {
    render(<SelecionarLocalizacao onChange={() => {}} value="" />);
    expect(screen.getByRole('combobox')).toBeInTheDocument();
    expect(screen.getByRole('option', { name: /Sua localização/i })).toBeInTheDocument();
    expect(screen.getByRole('option', { name: /Aracaju - SE/i })).toBeInTheDocument();
    // Adicione mais expects para outras opções se necessário
  });

  it('chama a função onChange ao selecionar uma opção', () => {
    const onChangeMock = jest.fn();
    render(<SelecionarLocalizacao onChange={onChangeMock} value="" />);

    fireEvent.change(screen.getByRole('combobox'), { target: { value: '1' } }); // Seleciona Aracaju - SE

    expect(onChangeMock).toHaveBeenCalledTimes(1);
    expect(onChangeMock).toHaveBeenCalledWith(expect.any(Object)); // Verifica se o evento é passado
  });


    it('exibe o valor inicial correto', () => {
        render(<SelecionarLocalizacao onChange={() => { }} value="2" />); // Define Belém - PA como valor inicial

        expect(screen.getByRole('combobox')).toHaveValue('2'); // Verifica se o valor inicial é Belém - PA
    });

});