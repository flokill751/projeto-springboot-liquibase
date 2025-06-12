package com.eridanimelo.springliquibase.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "telefone")
@SequenceGenerator(name = "seq_telefone", sequenceName = "seq_telefone", initialValue = 1, allocationSize = 1)

public class Telefone {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_telefone")
    private Long id;

    @Column(name = "numero")
    private String mumeroT; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", foreignKey = @ForeignKey(name = "FK_PERSON_TELL")) 
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
    public String getMumeroT() {
        return mumeroT;
    }
    public void setMumeroT(String mumeroT) {
        this.mumeroT = mumeroT;
    }
    public Person getPerson() {
        return person;
    }
    public void setPerson(Person person) {
        this.person = person;
    }
}
