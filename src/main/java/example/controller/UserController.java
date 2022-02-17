package example.controller;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import example.model.AppUser;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;
  private final ObjectMapper objectMapper;

  public UserController(UserService userService, ObjectMapper objectMapper) {
    this.userService = userService;
    this.objectMapper = objectMapper;
  }

  @PostMapping("/signin")
  public String login(
      @RequestParam String username,
      @RequestParam String password) {
    return userService.signin(username, password);
  }

  @PostMapping("/signup")
  public String signup(@RequestBody AppUser user) {
    return userService.signup(objectMapper.convertValue(user, AppUser.class));
  }

  @DeleteMapping(value = "/{username}")
  public String delete(@PathVariable String username) {
    userService.delete(username);
    System.out.println("user deleted: " + username);
    return username;
  }

  @GetMapping(value = "/{username}")
  public String search(@PathVariable String username) throws JsonProcessingException {
    return objectMapper.writeValueAsString(userService.search(username));
  }

  @GetMapping(value = "/whoami")
  public String whoami(HttpServletRequest req) throws JsonProcessingException {
    return objectMapper.writeValueAsString(userService.whoami(req));
  }

  @GetMapping("/refresh")
  public String refresh(HttpServletRequest req) {
    return userService.refresh(req.getRemoteUser());
  }

}
