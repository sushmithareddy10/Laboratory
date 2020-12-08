package com.cg.healthassistlaboratory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * creating HealthAssistLaboratoryApplication class which is a main class which
 * is used to Run the Application
 *
 */
@EnableSwagger2
@SpringBootApplication
public class HealthAssistLaboratoryApplication {
	/**
	 * creating private static final logger variable to create logger object
	 */
	private static final Logger logger = LoggerFactory.getLogger(HealthAssistLaboratoryApplication.class);

	/**
	 * creating void main class to indicate JVM to run the Application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		logger.info("OPEN");
		SpringApplication.run(HealthAssistLaboratoryApplication.class, args);
	}
//	/**
//	 * creating postApi method which returns the Docket for swagger
//	 * 
//	 * @return
//	 */
//	public Docket postApi() {
//		return new Docket(DocumentationType.SWAGGER_2).apiInfo(metadata()).select().paths(regex("/api.*")).build();
//	}
//
//	/**
//	 * creating metadata method which returns ApiInfo which tells about the project
//	 * 
//	 * @return
//	 */
//	private ApiInfo metadata() {
//		return new ApiInfoBuilder().title("# Medical Health Assis Laboratory #")
//				.description("Doctor, MedicalTest and AppoinmentMedicalTest").build();
//	}
}
