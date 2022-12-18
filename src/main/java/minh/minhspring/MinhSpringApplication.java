package minh.minhspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Build - cmd
 * cd D:\프로젝트\spring-study\minh-spring
 * gradlew build
 * cd build\libs
 * doskey ls = dir
 * ls -arlth
 * java -jar minh-spring-0.0.1-SNAPSHOT.jar
 *
 * Build 폴더 삭제
 * cd D:\프로젝트\spring-study\minh-spring
 * gradlew clean build
 */

@SpringBootApplication
public class MinhSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinhSpringApplication.class, args);
	}

}
