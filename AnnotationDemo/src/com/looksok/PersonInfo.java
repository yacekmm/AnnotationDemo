package com.looksok;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class PersonInfo {

	@PersonalData(isSensitive=false) 
	private String name;

	@PersonalData(isSensitive=false) 
	private String surname;

	@PersonalData(isSensitive=true) 
	private String email;

	@PersonalData(isSensitive=true) 
	private String phoneNumber;
	
	private long storageId;

	public PersonInfo(String name, String surname, String email, String phoneNumber, long storageId) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.storageId = storageId;
	}

	public String toString(boolean includeSensitiveData){
		StringBuilder sb = new StringBuilder("Person info with sensitive data ");
		sb.append(includeSensitiveData ? "INCLUDED:\n" : "EXCLUDED:\n");

		String annotatedFields = processAnnotations(includeSensitiveData);
		sb.append(annotatedFields);
		return sb.toString();
	}

	public String processAnnotations(boolean includeSensitiveData) {
		StringBuilder sb = new StringBuilder();
		try{
			for(Field classField : this.getClass().getDeclaredFields()) {
				for(Annotation annotation : classField.getAnnotations()) {
					if(annotation.annotationType() == PersonalData.class) {
						PersonalData personalData = (PersonalData) annotation;
						String result = printPersonalData(includeSensitiveData, classField, personalData);
						sb.append(result);
					}
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	
		return sb.toString();
	}

	private String printPersonalData(boolean includeSensitiveData, Field classField, PersonalData personalData)
			throws IllegalAccessException {
		StringBuilder sb = new StringBuilder();
		if(personalData.isSensitive()){
			if(includeSensitiveData){
				sb.append(buildText(classField));
			}
		}else{
			sb.append(buildText(classField));
		}
		return sb.toString();
	}

	private String buildText(Field f) throws IllegalArgumentException, IllegalAccessException {
		return f.getName() + ": " + f.get(this) + "\n";
	}
}
