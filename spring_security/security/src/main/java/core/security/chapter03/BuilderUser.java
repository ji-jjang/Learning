//package core.security.chapter03;
//
//
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//
//public class BuilderUser {
//    public static void main(String[] args) {
//        UserDetails u = User.withUsername("bill") // 주어진 사용자 이름으로 사용자 생성
//                .password("1234")
//                .authorities("read", "write")
//                .accountExpired((false))
//                .disabled(true)
//                .build();
//
//        User.UserBuilder builder2 = User.withUserDetails(u); // 기존의 UserDetail 인스턴스에서 사용자 생성
//        UserDetails u2 = builder2.build();
//    }
//}
