package org.example.models;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "departamentos")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dep")
    private long id;

    @NonNull
    @Column(name = "nombre_dep")
    private String nombre;

    @NonNull
    @Column(name = "localidad_dep")
    private String localidad;

    @OneToMany(mappedBy = "depElement", cascade = CascadeType.ALL)
    List<Empleado> empleados = new ArrayList<Empleado>();

    public void addEmpleados(Empleado empleado){
        this.empleados.add(empleado);
        empleado.setDepElement(this);
    }


}
