package com.rancard.springMon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringMonApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringMonApplication.class, args);
	}
//	@Bean
//	CommandLineRunner runner (StudentRepository repository, MongoTemplate mongoTemplate){
//		return args -> {
//			String email = "anim@gmail.com";
//			StudentAddress address = new StudentAddress("Ghana","2001","Accra");
//			StudentModel student = new StudentModel("Ansah","Anim",email,
//					StudentGender.MALE,address,
//					List.of("Computer Science"),
//					BigDecimal.TEN,
//					LocalDateTime.now()
//			);
//			repository.findStudentByEmail(email).ifPresentOrElse(s->{
//				System.out.println("Student with email" + s + " already exist");
//			}, ()->{
//				repository.insert(student);
//			});
////			Query query = new Query();
////			query.addCriteria(Criteria.where("email").is(email));
////			List<StudentModel> students = mongoTemplate.find(query, StudentModel.class);
////			if (students.size() > 1){
////				throw new IllegalStateException("Found students with same ID");
////			}
////			if (students.isEmpty()){
////				repository.insert(student);
////			}else{
////				System.out.println("Student with email" + email + " already exist");
////			}
//		};
//
//	}
}
