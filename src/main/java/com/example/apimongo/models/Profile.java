package com.example.apimongo.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;
import java.io.Serializable;

@Data
@Document(collection = "profiles")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Profile implements Serializable {

    private static final long serialVersionUID = 1234567L;

    @Id
    private String id;

    @NotBlank(message = "Name attribute is mandatory")
    @Length(max = 15, message = "Name attribute max length is 15")
    @Pattern(regexp = "^[A-Za-z]*$")
    private String name;

    @NotBlank(message = "Father's Lastname attribute is mandatory")
    @Length(max = 15, message = "Father's lastname attribute max length is 15")
    @Pattern(regexp = "^[A-Za-z]*$")
    private String fatherLastname;

    @NotBlank(message = "Mother's Lastname attribute is mandatory")
    @Length(max = 15, message = "Mother's lastname attribute max length is 15")
    @Pattern(regexp = "^[A-Za-z]*$")
    private String motherLastname;

    private String image;

    @NotNull
    @Pattern(regexp = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$")
    private String birthDate;

    @NotNull
    @Min(value = 12, message = "Age attribute must be older than 12")
    @Max(value = 999, message = "Invalid age attribute")
    private Integer age;

}
