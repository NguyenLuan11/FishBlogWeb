package com.tnluan.fishblogweb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    private String userName;

    private String avatarUrl;

    private String fullName;

    private String email;

    private String password;

    private String role;

    private String phone;

    private String address;

    private Date registeredDate;

    private Date modifiedDate;

    public UserDto(Long id, String userName, String avatarUrl, String fullName, String email, String role, String phone, String address, Date registeredDate, Date modifiedDate) {
        this.id = id;
        this.userName = userName;
        this.avatarUrl = avatarUrl;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.phone = phone;
        this.address = address;
        this.registeredDate = registeredDate;
        this.modifiedDate = modifiedDate;
    }

    public UserDto(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
