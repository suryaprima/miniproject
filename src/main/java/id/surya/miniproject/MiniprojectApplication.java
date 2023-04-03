package id.surya.miniproject;


import id.surya.miniproject.controller.MainController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MiniprojectApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(MiniprojectApplication.class, args);

		MainController mainController = applicationContext.getBean(MainController.class);
		mainController.run();
	}


}
