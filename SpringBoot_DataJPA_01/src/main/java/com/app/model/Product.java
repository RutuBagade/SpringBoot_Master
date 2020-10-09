package com.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Component
public class Product {
	@Id
	@GeneratedValue
	private Integer prodId;
	@NonNull
	private String prodCode;
	@NonNull
	private Double prodCost;
	@NonNull
	private String vendorCode;
}