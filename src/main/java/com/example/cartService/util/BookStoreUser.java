package com.example.cartService.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;


@Data
@NoArgsConstructor
public class BookStoreUser {

    @Id
    @GenericGenerator(name = "userData",strategy = "increment")
    @GeneratedValue(generator = "userData")
    private Long userId;
    private String firstName;
    private String lastName;
    private String emailId;
    private String password;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate dob;
    private LocalDate registeredDate;
    private LocalDate updateDate;
    private String kyc;
    private Boolean verify;
    private Long oneTimePassword;
    private LocalDate purchaseDate;
    private LocalDate expireDate;


}
