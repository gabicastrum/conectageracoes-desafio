package conectageracao.conectageracao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import conectageracao.conectageracao.entities.Atividade;

@Repository
public interface AtividadeRepositorio extends JpaRepository<Atividade, Long> {
        

        List<Atividade> findByVoluntarioId(Long voluntarioId);

        List<Atividade> findByIdosoId(Long idosoId);
}
