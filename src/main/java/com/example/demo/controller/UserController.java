// package com.example.demo.controller;

// import com.example.demo.dto.UserDTO;
// import com.example.demo.entity.User;
// import com.example.demo.service.UserService;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/users")
// public class UserController {

//     private final UserService userService;

//     public UserController(UserService userService) {
//         this.userService = userService;
//     }

//     // 🔐 ADMIN ONLY
//     @PreAuthorize("hasRole('ADMIN')")
//     @GetMapping
//     public List<UserDTO> getAll() {
//         return userService.getAllUsers()
//                 .stream()
//                 .map(this::mapToDTO)
//                 .toList();
//     }

//     // 🔐 ADMIN ONLY
//     @PreAuthorize("hasRole('ADMIN')")
//     @GetMapping("/{id}")
//     public UserDTO getById(@PathVariable Long id) {
//         User user = userService.getUser(id);
//         return mapToDTO(user);
//     }

//     private UserDTO mapToDTO(User user) {
//         return new UserDTO(
//                 user.getId(),
//                 user.getFullName(),
//                 user.getEmail(),
//                 user.getDepartment(),
//                 user.getRole(),
//                 user.getCreatedAt()
//         );
//     }
// }
