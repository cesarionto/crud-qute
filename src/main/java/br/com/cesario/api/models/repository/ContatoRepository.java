package br.com.cesario.api.models.repository;

import br.com.cesario.api.models.Contato;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ContatoRepository implements PanacheMongoRepository<Contato> {
}
