/*
 * Created on 2023-02-04 ( 14:40:48 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */
package com.example.calendar.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.example.calendar.config.TimeZoneConvert;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
@Table(name = "USERS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Users implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "ID")
	private String id;

	@Size(max = 64)
	@Column(name = "ACCOUNT", length = 64)
	private String account;

	@Email(message = "Email invalidate")
	@Column(name = "EMAIL", length = 128)
	private String email;

	@Size(max = 255)
	@Column(name = "PASSWORD", length = 255)
	private String password;
	
	@Column(name = "CREATED_AT")
	@CreatedDate
	@JsonSerialize(using = TimeZoneConvert.class)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@CreatedBy
	@Column(name = "CREATED_BY")
	private String createdBy;
	
	@Column(name = "UPDATED_AT")
	@LastModifiedDate
	@JsonSerialize(using = TimeZoneConvert.class)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
}
