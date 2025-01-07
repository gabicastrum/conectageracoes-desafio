
package conectageracao.conectageracao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import conectageracao.conectageracao.entities.Pessoa;

@Repository
public interface PessoaRepositorio extends JpaRepository<Pessoa, Long> {

    Pessoa findByemail(String email);
    Pessoa findBynome(String nome);
    Pessoa findByid(Long Id);


}