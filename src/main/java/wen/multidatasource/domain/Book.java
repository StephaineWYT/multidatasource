package wen.multidatasource.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "book")
@Data
public class Book {

    @Id
    @Column(name = "b_id")
    @GeneratedValue
    private Long id;

    @Column(name = "b_name")
    private String name;

}