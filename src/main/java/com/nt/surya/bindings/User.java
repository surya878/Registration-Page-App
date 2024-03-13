package com.nt.surya.bindings;

import java.util.Date;

import lombok.Data;

@Data
public class User {

	private Integer userId;
	private String userFName;
	private String userLName;
	private String userEmail;
	private Integer userPhone;
	private Date userDob;
	private String userGender;
	private String userCountry;
	private String userState;
	private String userCity;
	private String userPwd;
	private Boolean userAccStatus;
	private Date userCreatedDate;
	private Date userUpdatedDate;
}
