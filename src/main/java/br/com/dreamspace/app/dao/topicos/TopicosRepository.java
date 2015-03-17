package br.com.dreamspace.app.dao.topicos;

import br.com.dreamspace.app.entity.topicos.Topico;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Francisco on 15/03/2015.
 */
public interface TopicosRepository extends CrudRepository<Topico, Long> {
}
