package br.com.cesario.api.service;

import java.util.List;

import br.com.cesario.api.models.Contato;
import br.com.cesario.api.models.repository.ContatoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ContatoService {

    ContatoRepository contatoRepository;

    public ContatoService(ContatoRepository contatoRepository) {
        this.contatoRepository = contatoRepository;
    }

    public List<Contato> findAll() {
        return contatoRepository.findAll().list();
    }

    public Contato findById(String id) {
        return contatoRepository.findById(null == id ? null : new org.bson.types.ObjectId(id));
    }

    public Contato save(Contato contato) {
        contatoRepository.persist(contato);
        return contato;
    }

    public Contato update(Contato contato) {
        Contato existingContato = contatoRepository.findById(contato.getId());
        if (existingContato != null) {
            existingContato.nome = contato.nome;
            existingContato.telefones = contato.telefones;
            return existingContato;
        }
        return null;
    }

    public void delete(String id) {
        Contato contato = contatoRepository.findById(new org.bson.types.ObjectId(id));
        if (contato != null) {
            contatoRepository.delete(contato);
        }
    }

    public void deleteAll() {
        contatoRepository.deleteAll();
    }

}
