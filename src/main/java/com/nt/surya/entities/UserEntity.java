package com.nt.surya.entities;

import java.time.LocalDate;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.core.sym.Name;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "USER_DTLS")
public class UserEntity {

	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private Integer userId;

	@Column(name = "USER_FIRST_NAME")
	private String userFname;

	@Column(name = "USER_LAST_NAME")
	private String userLname;

	@Column(name = "USER_EMAIL", unique = true)
	private String userEmail;

	@Column(name = "USER_PHNO")
	private Integer userPhone;

	@Column(name = "USER_DOB")
	private Date userDob;

	@Column(name = "USER_GENDER")
	private String userGender;

	@Column(name = "USER_COUNTRY")
	private String userCountry;

	@Column(name = "USER_STATE")
	private String userState;

	@Column(name = "USER_CITY")
	private String userCity;

	@Column(name = "USER_PWD")
	private String userPwd;

	@Column(name = "USER_ACC_STATUS")
	private String userAccStatus;

	@Column(name = "USER_CREATED_DATE")
	@CreationTimestamp
	private LocalDate userCreatedDate;

	@Column(name = "USER_UPDATED_DATE")
	@UpdateTimestamp
	private LocalDate userUpdatedDate;

}
