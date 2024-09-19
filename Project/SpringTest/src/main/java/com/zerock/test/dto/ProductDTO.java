package com.zerock.test.dto;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ProductDTO {
	private int shop_id;
	private String products_seller;
	private int idx;
	private String products_name;
	private String products_category;
	private String products_status;
	private String products_details;
	private int products_price;
	private String products_location;
	private String products_img1;
	private String products_img2;
	private String products_img3;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date created_at;
	private int like_count;
	
	private String shop_owner;
	private String shop_name;
	private String shop_img;
	private int shop_star;
	private String  shop_info;
	

	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public int getShop_id() {
		return shop_id;
	}
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getProducts_name() {
		return products_name;
	}
	public void setProducts_name(String products_name) {
		this.products_name = products_name;
	}
	public String getProducts_category() {
		return products_category;
	}
	public void setProducts_category(String products_category) {
		this.products_category = products_category;
	}
	public String getProducts_status() {
		return products_status;
	}
	public void setProducts_status(String products_status) {
		this.products_status = products_status;
	}
	public String getProducts_details() {
		return products_details;
	}
	public void setProducts_details(String products_details) {
		this.products_details = products_details;
	}
	public int getProducts_price() {
		return products_price;
	}
	public void setProducts_price(int products_price) {
		this.products_price = products_price;
	}
	public String getProducts_location() {
		return products_location;
	}
	public void setProducts_location(String products_location) {
		this.products_location = products_location;
	}
	public String getProducts_img1() {
		return products_img1;
	}
	public void setProducts_img1(String products_img1) {
		this.products_img1 = products_img1;
	}
	public String getProducts_img2() {
		return products_img2;
	}
	public void setProducts_img2(String products_img2) {
		this.products_img2 = products_img2;
	}
	public String getProducts_img3() {
		return products_img3;
	}
	public void setProducts_img3(String products_img3) {
		this.products_img3 = products_img3;
	}
	public int getLike_count() {
		return like_count;
	}
	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}
	public String getProducts_seller() {
		return products_seller;
	}
	public void setProducts_seller(String products_seller) {
		this.products_seller = products_seller;
	}
	public String getShop_owner() {
		return shop_owner;
	}
	public void setShop_owner(String shop_owner) {
		this.shop_owner = shop_owner;
	}
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public String getShop_img() {
		return shop_img;
	}
	public void setShop_img(String shop_img) {
		this.shop_img = shop_img;
	}
	public int getShop_star() {
		return shop_star;
	}
	public void setShop_star(int shop_star) {
		this.shop_star = shop_star;
	}
	public String getShop_info() {
		return shop_info;
	}
	public void setShop_info(String shop_info) {
		this.shop_info = shop_info;
	}
	
	 
}
