package com.example.demo.View;

import com.example.demo.Model.Employee;
import com.example.demo.Services.EmployeeService;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.crud.impl.GridCrud;


@Route("")
public class EmployeeView extends VerticalLayout {

    public EmployeeView(EmployeeService service){

        // crud instance
        GridCrud<Employee> crud = new GridCrud<>(Employee.class);

        // grid configuration
        crud.getGrid().setColumns("register", "firstName", "lastName", "annualSalary", "tax");
        crud.getGrid().setColumnReorderingAllowed(true);
        crud.getCrudFormFactory().setVisibleProperties(CrudOperation.ADD, "register", "firstName", "lastName", "annualSalary");
        crud.getCrudFormFactory().setVisibleProperties(CrudOperation.UPDATE, "firstName", "lastName", "annualSalary");

        // layout configuration
        setSizeFull();
        add(crud);
        crud.setFindAllOperationVisible(false);

        // logic configuration
        crud.setOperations(
                () -> service.findAll(),
                Employee -> service.add(Employee),
                Employee -> service.update(Employee),
                Employee -> service.delete(Employee)
        );

    }
}
