package core.security.chapter06;

//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.List;
//
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    private String username;
//    private String password;
//
//    @Enumerated(EnumType.STRING)
//    private EncryptionAlgorithm algorithm;
//
//    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
//    private List<Authority> authorities;
//}
