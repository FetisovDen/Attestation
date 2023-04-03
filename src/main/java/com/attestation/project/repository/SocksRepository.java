package com.attestation.project.repository;

import com.attestation.project.entity.SocksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SocksRepository extends JpaRepository<SocksEntity, Long> {

    SocksEntity findSocksEntitiesByColorAndCottonPart(String color, Integer cottonPart);

    @Query(nativeQuery = true, value = "SELECT SUM(quantity) FROM socks WHERE color = :color AND cotton_part > :cottonPart")
    Integer findAllQuantityByColorAndCottonPartMoreThan(@Param(value = "color") String color, @Param(value = "cottonPart") Integer cottonPart);

    @Query(nativeQuery = true, value = "SELECT SUM(quantity) FROM socks WHERE color = :color AND cotton_part < :cottonPart")
    Integer findAllQuantityByColorAndCottonPartIsLessThan(@Param(value = "color") String color, @Param(value = "cottonPart") Integer cottonPart);

}