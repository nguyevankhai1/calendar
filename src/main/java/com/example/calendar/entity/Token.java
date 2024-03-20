package com.example.calendar.entity;

import java.io.Serializable;
import java.util.Date;

import com.example.calendar.config.TimeZoneConvert;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TOKEN")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Token implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "ID")
	private String id;

	@Column(name = "TOKEN", length = 1000)
	private String token;


	@JsonSerialize(using = TimeZoneConvert.class)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EXPIRED_DATE")
	private Date expiredDate;

	@ManyToOne
	@JoinColumn(name = "USERS_ID",insertable=false, updatable=false , foreignKey = @ForeignKey(name = "FK_USERS_ID_TOKEN"))
	public Users user;

}