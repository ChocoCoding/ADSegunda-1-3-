package org.example.models;


import jakarta.persistence.*;
import lombok.*;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "empleados")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_emp")
    private long id;

    @NonNull
    @Column(name = "nombre_emp")
    private String nombre;

    @NonNull
    @Column(name = "puesto_emp")
    private String puesto;

    @NonNull
    @Column(name = "sueldo_emp")
    private double sueldo;

    @NonNull
    @Column(name = "edad_emp")
    private int edad;

    @NonNull
    @Column(name = "dni_emp")
    private String dni;

    @NonNull
    @Column(name = "esJefe_emp")
    private boolean esJefe;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "dpto_id")
    Departamento depElement;




}
