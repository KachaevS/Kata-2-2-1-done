package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);


      userService.add
              (new User(new Car ("VAZ", 2110), "User1", "Lastname1", "user1@mail.ru"));
      userService.add
              (new User(new Car ("VAZ", 2111),"User2", "Lastname2", "user2@mail.ru"));
      userService.add
              (new User(new Car("VAZ",2112),"James", "Brown", "user3@mail.ru"));
      userService.add
              (new User(new Car("VAZ", 2113),"User4", "Lastname4", "user4@mail.ru"));


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println(user.toStringFull());
      }

      System.out.println("Запрос на поиск пользователя по автомобилю выполнен:");
      System.out.println(userService.getUserByCarModelAndSeries("VAZ",2112).toString());

      context.close();
   }
}
