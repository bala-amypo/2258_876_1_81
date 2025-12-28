@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager,
                          UserService userService,
                          JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    // ✅ USER registration (PUBLIC)
    @PostMapping("/register")
    public UserResponse registerUser(@RequestBody RegisterRequest request) {

        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setDepartment(request.getDepartment());
        user.setPassword(request.getPassword());
        user.setRole("USER"); // 🔒 forced USER

        User saved = userService.registerUser(user);
        return mapToResponse(saved);
    }

    // ✅ ADMIN registration (PROTECTED)
    @PostMapping("/register/admin")
    public UserResponse registerAdmin(@RequestBody RegisterRequest request) {

        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setDepartment(request.getDepartment());
        user.setPassword(request.getPassword());
        user.setRole("ADMIN"); // 🔥 forced ADMIN

        User saved = userService.registerUser(user);
        return mapToResponse(saved);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()
                )
        );

        User user = userService.getByEmail(request.getEmail());
        String token = jwtUtil.generateTokenForUser(user);
        return Map.of("token", token);
    }

    private UserResponse mapToResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getDepartment(),
                user.getRole(),
                user.getCreatedAt()
        );
    }
}
