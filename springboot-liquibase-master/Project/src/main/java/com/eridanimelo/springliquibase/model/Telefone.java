package com.eridanimelo.springliquibase.model;

import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "telefone")
@SequenceGenerator(name = "seq_telefone", sequenceName = "seq_telefone", initialValue = 1, allocationSize = 1)
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_telefone")
    @Schema(description = "ID do telefone")
    private Long id;

    @Column(name = "numero")
    @Schema(description = "Número do telefone")
    private String numeroT;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", foreignKey = @ForeignKey(name = "FK_PERSON_TELL"))
    @Schema(description = "Pessoa associada ao telefone")
    private Person person;

    public Telefone() {
    }

    public Telefone(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroT() {
        return numeroT;
    }

    public void setNumeroT(String numeroT) {
        this.numeroT = numeroT;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
