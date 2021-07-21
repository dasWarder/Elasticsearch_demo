package by.itechart.elasticsearch_demo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto implements Serializable {

    private String id;

    private String name;

    private String surname;
}
