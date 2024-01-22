package org.example;

import org.example.models.Departamento;
import org.example.models.Empleado;
import org.example.repositories.DepartamentoRepository;
import org.example.repositories.EmpleadoRepository;
import org.example.utilities.HibernateUtil;
import org.hibernate.Session;

public class App {
    public static void main(String[] args) {
        Session session = HibernateUtil.get().openSession();

        DepartamentoRepository departamentoRepository = new DepartamentoRepository(session);
        EmpleadoRepository empleadoRepository = new EmpleadoRepository(session);

        //Creamos los departamentos
        Departamento d1 = new Departamento("Departamento1","Vigo");
        Departamento d2 = new Departamento("Departamento2","Vigo");
        Departamento d3 = new Departamento("Departamento3","A Coruña");
        Departamento d4 = new Departamento("Departamento4","A Coruña");
        Departamento d5 = new Departamento("Departamento5","Lugo");

        //Creamos los empleados
        Empleado emp1 = new Empleado("Emp 1", "Ingeniero", 4100, 45, "12345678A", true);
        Empleado emp2 = new Empleado("Emp 2", "Técnico", 1000, 18, "12345678B", false);
        Empleado emp3 = new Empleado("Emp 3", "Ingeniero", 4100, 50, "12345678C", true);
        Empleado emp4 = new Empleado("Emp 4", "Técnico", 1500, 30, "12345678D", false);
        Empleado emp5 = new Empleado("Emp 5", "Ingeniero", 2000, 35, "12345678E", false);
        Empleado emp6 = new Empleado("Emp 6", "Técnico", 1250, 20, "12345678F", false);
        Empleado emp7 = new Empleado("Emp 7", "Ingeniero", 4100, 40, "12345678G", false);
        Empleado emp8 = new Empleado("Emp 8", "Técnico", 1300, 22, "12345678H", false);
        Empleado emp9 = new Empleado("Emp 9", "Ingeniero", 2750, 43, "12345678I", false);
        Empleado emp10 = new Empleado("Emp 10", "Técnico", 1450, 25, "12345678J", false);

        //Añadimos los empleados para guardarlos en la lista de cada departamento
        d1.addEmpleados(emp1);
        d1.addEmpleados(emp2);

        d2.addEmpleados(emp3);
        d2.addEmpleados(emp4);

        d3.addEmpleados(emp5);
        d3.addEmpleados(emp6);

        d4.addEmpleados(emp7);
        d4.addEmpleados(emp8);

        d5.addEmpleados(emp9);
        d5.addEmpleados(emp10);

        //Hacemos a los departamentos persistentes
        departamentoRepository.guardar(d1);
        departamentoRepository.guardar(d2);
        departamentoRepository.guardar(d3);
        departamentoRepository.guardar(d4);
        departamentoRepository.guardar(d5);

        departamentoRepository.visualizarDepartamento(d5);


        System.out.println("Finalizando la conexion a MySQL");

        departamentoRepository.addEmpleadoDepart(3,new Empleado("Emp 11", "Técnico", 1450, 25, "12345678J", false));

        empleadoRepository.actualizarJefeDepartamento(3L,11L);
        departamentoRepository.visualizarDepartamento(d3);

        departamentoRepository.eliminar(4L);
        departamentoRepository.findbyId(4L);

        empleadoRepository.listaTecnicos();
        empleadoRepository.devolverEmpleadoMayorSueldo();

        empleadoRepository.aumentarSalarioJefes2(400);

        departamentoRepository.borrarEmpleadosDeUnDep(1L);
        session.close();
    }
}