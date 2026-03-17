package com.pharos.backend.entitys;

import java.time.LocalDateTime;

import org.hibernate.envers.Audited;

import com.pharos.backend.enums.EstadoTarjetaEnum;
import com.pharos.backend.enums.TipoTarjetaEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tarjeta")
@Audited
@Data
public class Tarjeta extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tId")
    private Long id;
    
    @Column(name = "tIdentificador", nullable = false, unique = true, length = 64)
    private String identificador;

    @Column(name = "tPan", nullable = false, length = 19)
    private String pan;

    @Column(name = "tNombreTitular", nullable = false, length = 100)
    private String nombreTitular;

    @Column(name = "tNumeroDocumento", nullable = false, length = 20)
    private String numeroDocumento;

    @Column(name = "tTipoTarjeta", nullable = false, length = 20)
    private TipoTarjetaEnum tipoTarjeta;

    @Column(name = "tTelefono", nullable = false, length = 10)
    private String telefono;

    @Column(name = "tNumeroValidacion", nullable = false)
    private Integer numeroValidacion;

    @Column(name = "tEstado", nullable = false, length = 20)
    private EstadoTarjetaEnum estado;

    @Column(name = "tFechaInactivacion")
    private LocalDateTime fechaInactivacion;
}
