package org.example.libis.model;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Member {
    private int member_id;
    private String member_number;
    private String name;
    private String surname;
    private String email;
    private String username;
    private String password;
    private Role role;



    public Member( String name, String surname, String email, String username, String password,Role role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }





}
