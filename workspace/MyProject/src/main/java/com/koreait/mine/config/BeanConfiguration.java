package com.koreait.mine.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.koreait.mine.command.board.DeleteBoardCommand;
import com.koreait.mine.command.board.DownloadCommand;
import com.koreait.mine.command.board.InsertBoardCommand;
import com.koreait.mine.command.board.SelectBoardListCommand;
import com.koreait.mine.command.board.SelectBoardViewCommand;
import com.koreait.mine.command.board.UpdateBoardCommand;
import com.koreait.mine.command.member.EmailAuthCommand;
import com.koreait.mine.command.member.FindIdCommand;
import com.koreait.mine.command.member.FindPwCommand;
import com.koreait.mine.command.member.IdCheckCommand;
import com.koreait.mine.command.member.JoinCommand;
import com.koreait.mine.command.member.LeaveCommand;
import com.koreait.mine.command.member.LoginCommand;
import com.koreait.mine.command.member.LogoutCommand;
import com.koreait.mine.command.member.PresentPwCheckCommand;
import com.koreait.mine.command.member.UpdateMemberCommand;
import com.koreait.mine.command.member.UpdatePwCommand;

@Configuration
public class BeanConfiguration {

	@Bean
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@127.0.0.1:1521:xe");
		dataSource.setUsername("spring");
		dataSource.setPassword("1111");
		return dataSource;
	}
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource());
		sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/koreait/mine/dao/*.xml"));
		return sqlSessionFactory.getObject();
	}
	@Bean
	public SqlSessionTemplate sqlSession() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("utf-8");
		multipartResolver.setMaxUploadSize(1024 * 1024 * 10);  // 바이트 단위(10MB)
		return multipartResolver;
	}
	@Bean
	public IdCheckCommand idCheckCommand() {
		return new IdCheckCommand();
	}
	@Bean
	public EmailAuthCommand emailAuthCommand() {
		return new EmailAuthCommand();
	}
	@Bean
	public JoinCommand joinCommand() {
		return new JoinCommand();
	}
	@Bean
	public LoginCommand loginCommand() {
		return new LoginCommand();
	}
	@Bean
	public LogoutCommand logoutCommand() {
		return new LogoutCommand();
	}
	@Bean
	public LeaveCommand leaveCommand() {
		return new LeaveCommand();
	}
	@Bean
	public UpdateMemberCommand updateMemberCommand() {
		return new UpdateMemberCommand();
	}
	@Bean
	public PresentPwCheckCommand presentPwCheckCommand() {
		return new PresentPwCheckCommand();
	}
	@Bean
	public UpdatePwCommand updatePwCommand() {
		return new UpdatePwCommand();
	}
	@Bean
	public FindIdCommand findIdCommand() {
		return new FindIdCommand();
	}
	@Bean
	public FindPwCommand findPwCommand() {
		return new FindPwCommand();
	}
	
	
	
	
	@Bean
	public SelectBoardListCommand listCommand() {
		return new SelectBoardListCommand();
	}
	@Bean
	public InsertBoardCommand insertCommand() {
		return new InsertBoardCommand();
	}
	@Bean
	public DownloadCommand downloadCommand() {
		return new DownloadCommand();
	}
	@Bean
	public SelectBoardViewCommand selectBoardViewCommand() {
		return new SelectBoardViewCommand();
	}
	@Bean
	public UpdateBoardCommand updateBoardCommand() {
		return new UpdateBoardCommand();
	}
	@Bean
	public DeleteBoardCommand deleteBoardCommand() {
		return new DeleteBoardCommand();
	}
	
	
	
	
	
	
}