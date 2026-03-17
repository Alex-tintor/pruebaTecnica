package com.pharos.backend.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pharos.backend.entitys.Transaccion;

public interface TransaccionRepository extends JpaRepository<Transaccion,Long> {

    @Query("SELECT T FROM Transaccion T WHERE T.identificador = :referencia")
    Optional<Transaccion> busacarPorReferencia(@Param(value = "referencia") String referencia);
    
}
