package br.com.cesario.api.controllers;

import br.com.cesario.api.models.Contato;
import br.com.cesario.api.models.Telefone;
import br.com.cesario.api.service.ContatoService;
import io.quarkus.qute.Template;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/contatos")
public class ContatoController {

    @Inject
    ContatoService service;

    @Inject
    Template contatos; // lista
    @Inject
    Template contatoForm; // formulário

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String list() {
        return contatos.data("contatos", service.findAll()).render();
    }

    @GET
    @Path("/novo")
    @Produces(MediaType.TEXT_HTML)
    public String novo() {
        return contatoForm.data("contato", new Contato()).render();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void criar(@FormParam("nome") String nome,
                      @FormParam("tipo") String tipo,
                      @FormParam("numero") String numero) {

        Contato c = new Contato();
        c.nome = nome;

        var t = new Telefone();
        t.tipo = tipo;
        t.numero = numero;
        c.telefones = java.util.List.of(t);

        service.save(c);
    }

    @GET
    @Path("/delete/{id}")
    public void delete(@PathParam("id") String id) {
        service.delete(id);
    }
}