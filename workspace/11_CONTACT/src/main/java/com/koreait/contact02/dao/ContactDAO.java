package com.koreait.contact02.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.koreait.contact02.dto.Contact;

public class ContactDAO {

	private JdbcTemplate template;
	
	public ContactDAO(JdbcTemplate template) {
		this.template = template;
	}
		
	// 1. list
	public List<Contact> selectContactList() {
		String sql = "SELECT NO, NAME, TEL, ADDR, EMAIL, NOTE FROM CONTACT ORDER BY NO DESC";
		return template.query(sql, new BeanPropertyRowMapper<Contact>(Contact.class));
	}
	
	// 2. view
	public Contact selectContactByNo(final long no) {
		String sql = "SELECT NO, NAME, TEL, ADDR, EMAIL, NOTE FROM CONTACT WHERE NO = ?";
		return template.queryForObject(sql, new BeanPropertyRowMapper<Contact>(Contact.class), no);
	}
	
	// 3. insert
	public int insertContact(final Contact contact) {
		String sql = "INSERT INTO CONTACT VALUES (CONTACT_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";
		return template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, contact.getName());
				ps.setString(2, contact.getTel());
				ps.setString(3, contact.getAddr());
				ps.setString(4, contact.getEmail());
				ps.setString(5, contact.getNote());
			}
		});
	}
	
	// 4. update
	public int updateContact(final Contact contact) {
		String sql = "UPDATE CONTACT SET NAME = ?, TEL = ?, ADDR = ?, EMAIL = ?, NOTE = ? WHERE NO = ?";
		return template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, contact.getName());
				ps.setString(2, contact.getTel());
				ps.setString(3, contact.getAddr());
				ps.setString(4, contact.getEmail());
				ps.setString(5, contact.getNote());
				ps.setLong(6, contact.getNo());
			}
		});
	}
	
	// 5. delete
	public int deleteContact(final long no) {
		String sql = "DELETE FROM CONTACT WHERE NO = ?";
		return template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setLong(1, no);
			}
		});
	}
	
}