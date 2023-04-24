package com.skypro.warehouse.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Data
@Entity
public class Socks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String color;
    private int cottonPart;
    private int quantity;

}
