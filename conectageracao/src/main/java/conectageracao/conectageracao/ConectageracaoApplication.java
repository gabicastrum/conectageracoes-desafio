package conectageracao.conectageracao;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import conectageracao.conectageracao.entities.Atividade;
import conectageracao.conectageracao.entities.Pessoa;
import conectageracao.conectageracao.entities.PessoaRequestDTO;
import conectageracao.conectageracao.repositories.AtividadeRepositorio;
import conectageracao.conectageracao.repositories.PessoaRepositorio;

@SpringBootApplication
public class ConectageracaoApplication  {

	public static void main(String[] args) {
		SpringApplication.run(ConectageracaoApplication.class, args);
	}

//	@Autowired
//	private PessoaRepositorio repositorio;

	//@Autowired
	//private AtividadeRepositorio atividades;

	@Bean
	public CommandLineRunner demo(PessoaRepositorio repositorio) {
		return (args) -> {
			
		};
	}
}
	/*
	@Override
	public void run(String... args) throws Exception {

	 	PessoaRequestDTO pessoaNova = new PessoaRequestDTO( "John Doe","john.doe@email.com","senha123","Porto Alegre - RS","idoso" );
		repositorio.save(new Pessoa((pessoaNova)));
		PessoaRequestDTO pessoaNova2 = new PessoaRequestDTO( "bruno","bwx@jumail.com","1278","Porto Alegre - RS","jovem" );
		repositorio.save(new Pessoa((pessoaNova2)));

		PessoaRequestDTO pessoaNova3 = new PessoaRequestDTO(
    "Mariana Souza", 
    "mariana.souza@email.com", 
    "1234abcd", 
    "Curitiba - PR", 
    "adulto"
);
repositorio.save(new Pessoa(pessoaNova3));

// Exemplo 4: Pessoa Adolescente
PessoaRequestDTO pessoaNova4 = new PessoaRequestDTO(
    "Lucas Oliveira", 
    "lucas.oliveira@email.com", 
    "senhaAdolescente", 
    "São Paulo - SP", 
    "adolescente"
);
repositorio.save(new Pessoa(pessoaNova4));

// Exemplo 5: Pessoa Idosa com dados mais específicos
PessoaRequestDTO pessoaNova5 = new PessoaRequestDTO(
    "Maria Alves", 
    "maria.alves@email.com", 
    "senhaIdosa123", 
    "Salvador - BA", 
    "idoso"
);
repositorio.save(new Pessoa(pessoaNova5));

// Exemplo 6: Pessoa Jovem com interesse em tecnologia
PessoaRequestDTO pessoaNova6 = new PessoaRequestDTO(
    "Carlos Pereira", 
    "carlos.pereira@email.com", 
    "tech1234", 
    "Rio de Janeiro - RJ", 
    "jovem"
);
repositorio.save(new Pessoa(pessoaNova6));

// Exemplo 7: Pessoa Adulto com dados de um evento
PessoaRequestDTO pessoaNova7 = new PessoaRequestDTO(
    "Fernanda Costa", 
    "fernanda.costa@email.com", 
    "senha4321", 
    "Florianópolis - SC", 
    "adulto"
);
repositorio.save(new Pessoa(pessoaNova7));

// Exemplo 8: Pessoa Adolescente com nome fictício
PessoaRequestDTO pessoaNova8 = new PessoaRequestDTO(
    "Gustavo Lima", 
    "gustavo.lima@email.com", 
    "senhaAdolescente#123", 
    "Belo Horizonte - MG", 
    "adolescente"
);
repositorio.save(new Pessoa(pessoaNova8));

// Exemplo 9: Pessoa Idosa com histórico de saúde
PessoaRequestDTO pessoaNova9 = new PessoaRequestDTO(
    "José da Silva", 
    "jose.silva@email.com", 
    "senhaIdoso789", 
    "Recife - PE", 
    "idoso"
);
repositorio.save(new Pessoa(pessoaNova9));
		
		

	}
}
*/

/* Atividade atividadeinicial =  new Atividade("Acompanhar em consulta médica",
                "Gostaria de uma acompanhante para a minha consulta ao ortopedista",
                Set.of("saúde", "consulta", "acompanhamento"), "Hospital das Clínicas", "Presencial", LocalDate.now(),
                new Pessoa(), null, Atividade.StatusAtividade.PENDENTE);

		atividades.save(atividadeinicial);
 * 
 * 
 * 
*/