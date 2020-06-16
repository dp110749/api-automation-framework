package com.MLI_DOLPHIN.utilities;
import org.apache.commons.lang3.RandomStringUtils;

import com.github.javafaker.Faker;


public class RendomDataGenerator {
	
	public static Faker fakerapi ;
	
	public static String userName(){
		String generatedString =RandomStringUtils.randomAlphabetic(1);
			return("jhon"+generatedString); 		
	}
	/*This is function is used to generate 16 number */
	public static String Rendom_numberGenerator(){
		String generatedString =RandomStringUtils.randomNumeric(16);
			return(generatedString); 		
	}
	public static String rendom_numberGenerator(){
		String generatedString =RandomStringUtils.randomNumeric(2);
			return(generatedString); 		
	}
	public static String Fname(){
		
		fakerapi= new Faker();
		return fakerapi.name().firstName();
	}
	
	public static String Mname(){
		
		fakerapi= new Faker();
		return fakerapi.name().nameWithMiddle();		
	}
	
	public static String Lname(){		
		fakerapi= new Faker();
		return fakerapi.name().lastName();		
	}
	public static String MobNumber(){		
		fakerapi= new Faker();
		return fakerapi.phoneNumber().cellPhone();		
	}
	public static String address(){		
		fakerapi= new Faker();
		return fakerapi.address().city();		
	}
	
//	public static void main(String[] args) {
//		System.out.println("user name  =="+userName());
//		System.out.println("user  mob  =="+MobNumber());
//		System.out.println("user  fname  =="+Fname());
//		System.out.println("user  mname  =="+Mname());
//		System.out.println("user    lanem =="+Lname());
//		System.out.println("user  address =="+address());
//		System.out.println("user  mob  ==");
//		
//	}
//


}
