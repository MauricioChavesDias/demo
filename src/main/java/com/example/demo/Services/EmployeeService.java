package com.example.demo.Services;

import com.example.demo.Model.Employee;
import com.example.demo.Repositories.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

@Service
public class EmployeeService implements CrudListener<Employee> {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Employee> findAll(){
        return repository.findAll();
    }
    @Override
    public Employee add(Employee employee){
        incRegisterWhenNull(employee);
        updateTax(employee);
        return repository.save(employee);
    }
    @Override
    public Employee update(Employee employee){
        incRegisterWhenNull(employee);
        updateTax(employee);
        return repository.save(employee);
    }

    @Override
    public void delete(Employee employee){
         repository.delete(employee);
    }

    public void updateTax(Employee employee) {
         Double annualSalary = employee.getAnnualSalary();
         Double totalTaxToPay = 0.0;
        if (annualSalary >= 180000.01) {
            totalTaxToPay = calculateTax(annualSalary, 180000.00, 0.45, 54.097);
        } else if (annualSalary >= 90000.01) {
            totalTaxToPay = calculateTax(annualSalary, 90000.00, 0.37, 20.797);
        } else if (annualSalary >= 37000.01) {
            totalTaxToPay = calculateTax(annualSalary, 37000.00, 0.325, 3.572);
        } else if (annualSalary >= 18000.201) {
            totalTaxToPay = calculateTax(annualSalary, 18000.201, 0.19, 0.0);
        }
        else {totalTaxToPay = 0.0;}
        employee.setTax(formatDecimal(totalTaxToPay));
    }

    public Double calculateTax(Double annualSalary, Double TaxOnIncome, Double centsOver, Double taxValue) {
        Double overAnnualSalary = 0.0;
        Double totalToPay = 0.0;
        overAnnualSalary = (annualSalary - TaxOnIncome);
        totalToPay = (overAnnualSalary * centsOver)   + taxValue;
        return totalToPay;
    }

    public void incRegisterWhenNull(Employee employee) {
        if (employee.getRegister() == null) {
            Random rand = new Random();
            employee.setRegister(rand.nextInt(99999));
        };
    }

    public String formatDecimal(Double value) {
        DecimalFormat formatter = new DecimalFormat("#0.00");
        return formatter.format(value);
    }


}

