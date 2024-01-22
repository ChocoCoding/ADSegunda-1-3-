package org.example.repositories;

import org.example.models.Empleado;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class EmpleadoRepository implements IRepository<Empleado>{

    private Session session;

    public EmpleadoRepository(Session session){
        this.session = session;
    }

    @Override
    public void guardar(Empleado t) {
        Transaction trx = this.session.beginTransaction();
        session.persist(t);
        trx.commit();
    }

    @Override
    public Empleado findbyId(Long id) {
        return (Empleado) session.createMutationQuery("SELECT e FROM Empleado e WHERE e.id = :id").setParameter("id", id);
    }

    @Override
    public void eliminar(Long id) {

    }

    public void actualizarJefeDepartamento(Long idDep, long idEmpl){
        Transaction trx = this.session.beginTransaction();
        Query query =  this.session.createQuery("SELECT e FROM Empleado e WHERE e.depElement.id = :id AND e.id = :id2");
        query.setParameter("id",idDep);
        query.setParameter("id2",idEmpl);

        Empleado empleado = (Empleado) query.getSingleResult();

        empleado.setEsJefe(true);

        this.session.persist(empleado);
        trx.commit();
    }

    public void listaTecnicos(){
        Query query = session.createQuery("SELECT e FROM Empleado e WHERE e.puesto = 'Técnico'");
        List<Empleado> empleados = query.getResultList();

        System.out.println("Numero de empleados: " + empleados.size());

        for (Empleado e: empleados) {
            System.out.println("ID: " + e.getId() + "\n" +
                    "Nombre Empleado: " + e.getNombre() + "\n" +
                    "DNI: " + e.getDni() + "\n" +
                    "Puesto: " + e.getPuesto() + "\n" +
                    "Sueldo: " + e.getSueldo() + "\n" +
                    "Edad: " + e.getEdad() + "\n" +
                    "Es Jefe?: " + e.isEsJefe() + "\n" +
                    "Departamento: " + e.getDepElement().getNombre()
            );
        }
    }

    public List<Empleado> devolverEmpleadoMayorSueldo(){
        Query query = session.createQuery(    "SELECT e FROM Empleado e WHERE e.sueldo = (SELECT MAX(em.sueldo) FROM Empleado em)");


        List<Empleado> empleados = query.getResultList();
        System.out.println("Empleados con mayor sueldo: ");
        for (Empleado e: empleados) {
            System.out.println("ID: " + e.getId() + "\n" +
                    "Nombre Empleado: " + e.getNombre() + "\n" +
                    "DNI: " + e.getDni() + "\n" +
                    "Puesto: " + e.getPuesto() + "\n" +
                    "Sueldo: " + e.getSueldo() + "\n" +
                    "Edad: " + e.getEdad() + "\n" +
                    "Es Jefe?: " + e.isEsJefe() + "\n" +
                    "Departamento: " + e.getDepElement().getNombre()
            );
        }

    return empleados;
    }

    public void aumentarSalarioJefes(int cantidad){
        Transaction trx = session.beginTransaction();
        Query query = session.createQuery("UPDATE Empleado e set e.sueldo = e.sueldo + :cantidad where e.esJefe=true");
        query.setParameter("cantidad",cantidad);

        int elementoActualizados = query.executeUpdate();

        System.out.println("Se han modificado: " + elementoActualizados + " jefes");
        trx.commit();

    }

    public void aumentarSalarioJefes2(int cantidad){
        Transaction trx = session.beginTransaction();
        Query query = session.createQuery("SELECT e FROM Empleado e WHERE esJefe = true");
        List<Empleado> empleados = query.getResultList();

        System.out.println(empleados.size());
        for (Empleado e: empleados) {
            e.setSueldo(e.getSueldo() + cantidad);
            this.session.persist(e);
        }
        trx.commit();
    }



}
