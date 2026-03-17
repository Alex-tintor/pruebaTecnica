package com.pharos.backend.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pharos.backend.entitys.Tarjeta;

public interface TarjetaRepository extends JpaRepository<Tarjeta,Long> {

    @Query("SELECT T FROM Tarjeta T WHERE T.identificador = :identificador and T.fechaInactivacion IS NULL")
    Optional<Tarjeta> obtenerTarjetaPorIdentificadorSinAnulacion(@Param(value = "identificador") String identificador);
}
