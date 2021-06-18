package com.koreait.contact01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.koreait.contact01.command.ContactListCommand;
import com.koreait.contact01.command.ContactViewCommand;
import com.koreait.contact01.command.DeleteContactCommand;
import com.koreait.contact01.command.InsertContactCommand;
import com.koreait.contact01.command.UpdateContactCommand;

@Configuration
public class BeanConfiguration {

	@Bean
	public ContactListCommand contactListCommand() {
		return new ContactListCommand();
	}
	
	@Bean
	public ContactViewCommand contactViewCommand() {
		return new ContactViewCommand();
	}
	
	@Bean
	public InsertContactCommand insertContactCommand() {
		return new InsertContactCommand();
	}
	
	@Bean
	public UpdateContactCommand updateContactCommand() {
		return new UpdateContactCommand();
	}
	
	@Bean
	public DeleteContactCommand deleteContactCommand() {
		return new DeleteContactCommand();
	}
	
}
