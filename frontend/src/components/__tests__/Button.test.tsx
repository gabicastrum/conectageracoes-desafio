import { render, screen, fireEvent } from '@testing-library/react';
import Button from '../Button'; // Importe o componente Button
import '@testing-library/jest-dom'; // Import the jest-dom matchers

describe('Componente Button', () => {
  it('renderiza o botão com o texto correto', () => {
    render(<Button onClick={() => {}}>Clique aqui</Button>);
    expect(screen.getByRole('button', { name: /Clique aqui/i })).toBeInTheDocument();
  });

  it('chama a função onClick ao ser clicado', () => {
    const onClickMock = jest.fn();
    render(<Button onClick={onClickMock}>Clique aqui</Button>);
    fireEvent.click(screen.getByRole('button', { name: /Clique aqui/i }));
    expect(onClickMock).toHaveBeenCalledTimes(1);
  });


  it('desabilita o botão quando a prop disabled é true', () => {
    render(<Button onClick={() => {}} disabled={true}>Clique aqui</Button>);
    expect(screen.getByRole('button', { name: /Clique aqui/i })).toBeDisabled();
  });

  it('renderiza os filhos corretamente', () => {
    render(<Button onClick={() => {}}>
        <span>Um texto complexo</span>
        <i className="icon"></i> {/*Exemplo de ícone*/}
    </Button>);

    expect(screen.getByText('Um texto complexo')).toBeInTheDocument();
  });


});