package com.proathlete.persistance;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractEntityImpl implements AbstractEntity {


    private static final long serialVersionUID = -1667740081648166330L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id){
        this.id = id;
    }
}