package com.attestation.project.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "socks")
public class SocksEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String color;
    private Integer cottonPart;
    private Integer quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SocksEntity)) return false;
        SocksEntity that = (SocksEntity) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getColor(), that.getColor()) && Objects.equals(getCottonPart(), that.getCottonPart()) && Objects.equals(getQuantity(), that.getQuantity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getColor(), getCottonPart(), getQuantity());
    }
}
