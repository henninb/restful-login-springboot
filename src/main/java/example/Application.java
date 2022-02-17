package example;

import java.util.ArrayList;
import java.util.Collections;

import lombok.RequiredArgsConstructor;
import example.model.AppUser;
import example.model.AppUserRole;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import example.service.UserService;

@SpringBootApplication
@RequiredArgsConstructor
public class Application implements CommandLineRunner {

  final UserService userService;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Override
  public void run(String... params)  {
    AppUser admin = new AppUser();
    admin.setUsername("admin");
    admin.setPassword("admin");
    //admin.setEmail("admin@email.com");
    admin.setAppUserRoles(new ArrayList<>(Collections.singletonList(AppUserRole.ROLE_ADMIN)));

    userService.signup(admin);

    AppUser client = new AppUser();
    client.setUsername("client");
    client.setPassword("client");
    //client.setEmail("client@email.com");
    client.setAppUserRoles(new ArrayList<>(Collections.singletonList(AppUserRole.ROLE_CLIENT)));

    userService.signup(client);
  }

}
