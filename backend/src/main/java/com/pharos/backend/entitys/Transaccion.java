package com.pharos.backend.entitys;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.envers.Audited;

import com.pharos.backend.enums.EstadoTransaccionEnum;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Audited
@Data
@Table(name = "transaccion")
public class Transaccion extends Base {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tIdentificador", nullable = false, unique = true, length = 6)
    private String identificador;

    @Column(name = "tMonto", nullable = false, precision = 15, scale = 2)
    private BigDecimal monto;
    
    @Column(name = "tDireccionCompra", nullable = false, length = 255)
    private String direccionCompra;

    @Column(name = "tEstado", nullable = false, length = 20)
    private EstadoTransaccionEnum estado;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tTarjetaId")
    private Tarjeta tarjeta;

    @Column(name = "tFechaCompra")
    private LocalDateTime fechaAnulacion;

}
