package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car("VAZ", 2109);
      Car car2 = new Car("VAZ", 2110);
      Car car3 = new Car("VAZ", 2111);
      Car car4 = new Car("VAZ", 2112);

      userService.add(new User(car1, "User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User(car2,"User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User(car3,"James", "Brown", "user3@mail.ru"));
      userService.add(new User(car4,"User4", "Lastname4", "user4@mail.ru"));


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      }

      System.out.println("Запрос на поиск пользователя по автомобилю выполнен:");
      System.out.println(userService.getUserByCarModelAndSeries("VAZ",2111).toString());

      context.close();
   }
}
