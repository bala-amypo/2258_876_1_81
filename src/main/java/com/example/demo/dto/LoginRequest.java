// package com.example.demo.dto;

// public class LoginRequest {

//     private String email;
//     private String password;

//     public LoginRequest() {
//     }

//     public LoginRequest(String email, String password) {
//         this.email = email;
//         this.password = password;
//     }

//     public String getEmail() {
//         return email;
//     }

//     public String getPassword() {
//         return password;
//     }
// }

package com.example.demo.dto;

public class LoginRequest {
    private String email;
    private String password;

    public LoginRequest() {}

    // Constructor required by tests
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() { return email; }
    public String getPassword() { return password; }
}