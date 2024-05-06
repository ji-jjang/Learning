package com.juny.core;

import com.juny.core.ch07.Juny;
import com.juny.core.ch07.JunyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
//		(exclude = {DataSourceAutoConfiguration.class})
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}

	@Bean
	CommandLineRunner createJuny(JunyRepository junyRepository){
    return (args) -> {
      Juny spring = new Juny(null, "Spring", "john");
      Juny python = new Juny(null, "Python", "steve");
      junyRepository.save(spring);
      junyRepository.save(python);
    };
	}
}

//@SpringBootApplication
//@Slf4j
//@EnableConfigurationProperties(AppProperties.class)
//public class CoreApplication implements CommandLineRunner {
//
//	private static Logger logger = LoggerFactory.getLogger(CoreApplication.class);
//
//	public static void main(String[] args) {
//		Properties properties = new Properties();
//		properties.setProperty("spring.config.on-not-found", "IGNORE");
//		SpringApplication application = new SpringApplication(CoreApplication.class);
//		application.setDefaultProperties(properties);
//		ConfigurableApplicationContext applicationContext = application.run(args);
//
////		DbConfiguration dbConfiguration = applicationContext.getBean(DbConfiguration.class);
//		DbConfigurationYaml dbConfiguration = applicationContext.getBean(DbConfigurationYaml.class);
//		AppService appService = applicationContext.getBean(AppService.class);
//
//		log.info(appService.getAppProperties().toString());
//
//		log.info(dbConfiguration.toString());
//
//		log.info("CourseTrackerApplication started successfully with Log4j2 configuration");
//		logger.info("CourseTrackerApplication started successfully with Log4j2 configuration");
//	}
//
//	@Bean
//	public CommandLineRunner commandLineRunner() {
//		return args -> {
//			log.info("CommandLineRunner executed as a bean definition with " + args.length + " arguments");
//			for (int i = 0; i < args.length; i++) {
//				log.info("Argument: " + args);
//			}
//		};
//	}
//
//	@Override
//	public void run(String... args) throws Exception {
//
//	}
//	@Override
//	public void run(String... args) throws Exception {
//		log.info("CourseTrackerApplication CommandLineRunner has executed");
//	}

//	public static void main(String[] args) {
//		SpringApplication springApplication = new SpringApplication(CoreApplication.class);
//		springApplication.addListeners(new ApplicationStartingEventListener());
//		SpringApplication.run(CoreApplication.class, args);
//	}
//
//	@EventListener(ApplicationReadyEvent.class)
//	public void applicationReadyEvent(ApplicationReadyEvent applicationReadyEvent) {
//		System.out.println("Application Ready Event generated at " + new Date(applicationReadyEvent.getTimestamp()));
//	}
//
//
//}