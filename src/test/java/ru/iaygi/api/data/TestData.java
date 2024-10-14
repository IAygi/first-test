package ru.iaygi.api.data;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class TestData {

    private Integer id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;
}
