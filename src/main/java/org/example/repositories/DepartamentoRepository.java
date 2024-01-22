package org.example.repositories;

import jakarta.persistence.NoResultException;
import org.example.models.Departamento;
import org.example.models.Empleado;
import org.hibernate.*;
public class DepartamentoRepository implements IRepository<Departamento>{

    private Session session;

    public DepartamentoRepository(Session session){
        this.session = session;
    }


    @Override
    public void guardar(Departamento t) {
        Transaction trx = this.session.beginTransaction();
        session.persist(t);
        trx.commit();
    }

    @Override
    public Departamento findbyId(Long id) {
        Departamento departamento = null;
        try {
            departamento = (Departamento) session.createQuery("SELECT d from Departamento d WHERE d.id = :id").setParameter("id",id).getSingleResult();
        }catch (NoResultException ne){
            System.out.println("No existe el departamento " + id);
        }
        return departamento ;
    }

    @Override
    public void eliminar(Long id) {
        Transaction trx = this.session.beginTransaction();
        Departamento dep = findbyId(id);
        session.delete(dep);
        System.out.println("Se ha eliminado el departamento " + id);
        trx.commit();
    }


    public void visualizarDepartamento(Departamento d){
        try {
            System.out.println("Departamento: " + d.getId());
            System.out.println(
                    "ID: " + d.getId() + "\n" +
                            "Nombre Departamento: " + d.getNombre() + "\n" +
                            "ID Departamento: " + d.getLocalidad() + "\n"
            );
            System.out.println();

            for (Empleado e : d.getEmpleados()) {
                System.out.println("Empleado: " + e.getNombre());
                System.out.println(
                        "ID: " + e.getId() + "\n" +
                                "Nombre Empleado: " + e.getNombre() + "\n" +
                                "DNI: " + e.getDni() + "\n" +
                                "Puesto: " + e.getPuesto() + "\n" +
                                "Sueldo: " + e.getSueldo() + "\n" +
                                "Edad: " + e.getEdad() + "\n" +
                                "Es Jefe?: " + e.isEsJefe()
                );
                System.out.println();
            }
        }catch (Exception e){
            System.out.println("El departamento no existe");
        }

    }

    public void addEmpleadoDepart(long idDep,Empleado empleado){
        Transaction trn = this.session.beginTransaction();
        Departamento departamento = findbyId(idDep);
        departamento.addEmpleados(empleado);
        this.session.persist(departamento);
        this.session.persist(empleado);
        trn.commit();
    }

    public void borrarEmpleadosDeUnDep(Long idDep){
        Transaction trx = session.beginTransaction();
        Departamento departamento = findbyId(idDep);

        for (Empleado e : departamento.getEmpleados()) {
            session.remove(e);
        }

        trx.commit();
    }

}
