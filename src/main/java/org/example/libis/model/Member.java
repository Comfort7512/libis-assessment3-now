package org.example.libis.model;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Member {
    private int userId;
    private String member_number;
    private String name;
    private String surname;
    private String email;
    private String username;
    private String password;
    private Role role;



//    public User(int userId, String name, String surname, String email, String password) {
//        this.userId = userId;
//        this.name = name;
//        this.surname = surname;
//        this.email = email;
//        this.password = password;
//    }





}
