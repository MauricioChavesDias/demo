package com.example.demo.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = false)
public class Employee {


    @Id
    @EqualsAndHashCode.Include
    @NotNull
    private Integer register;

    private String firstName;

    private String lastName;

    private Double annualSalary;

    private String tax;


}
