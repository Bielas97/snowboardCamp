package com.app.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Newsletter {
    @Id
    @GeneratedValue
    private Long id;
    private String email;
    @Column(name = "chec_otrzymywania_konkursow")
    private Boolean yesOrNo;
}
