package br.com.cesario.api.models;

import java.util.List;

import org.bson.types.ObjectId;

import io.quarkus.mongodb.panache.PanacheMongoEntity;

public class Contato extends PanacheMongoEntity {
    public ObjectId id;
    public String nome;
    public List<Telefone> telefones;
}
