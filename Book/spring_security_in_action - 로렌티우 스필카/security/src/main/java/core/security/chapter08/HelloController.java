package core.security.chapter08;

import org.springframework.web.bind.annotation.*;

//@RestController
//public class HelloController {
//
//    @GetMapping("/hello")
//    public String hell() {
//        return "Hello!";
//    }
//
//    @GetMapping("/ciao")
//    public String ciao() {
//        return "Ciao!";
//    }
//
//    @GetMapping("/hola")
//    public String hola() {
//        return "Hola!";
//    }
//
//    @PostMapping("/a")
//    public String postEndpointA() {
//        return "Works!";
//    }
//
//    @GetMapping("/a")
//    public String getEndpointA() {
//        return "Works!";
//    }
//
//    @GetMapping("/a/b")
//    public String getEndpointB() {
//        return "Works!";
//    }
//
//    @GetMapping("/a/b/c")
//    public String getEndpointC() {
//        return "Works!";
//    }
//
//    @GetMapping("/product/{code}")
//    public String productCode(@PathVariable String code) {
//        return code;
//    }
//
//    @GetMapping("/video/{country}/{language}")
//    public String video(@PathVariable String country,
//                        @PathVariable String language) {
//        return "Video allowed for " + country + " " + language;
//    }
//}
