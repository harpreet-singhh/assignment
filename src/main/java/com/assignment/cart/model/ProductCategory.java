package com.assignment.cart.model;

public class ProductCategory {

	private Integer productCategoryId;

	private String name;

	private Double applicableTax;
	
	public ProductCategory() {
		// TODO Auto-generated constructor stub
	}

	public ProductCategory(Integer productCategoryId, String name, Double applicableTax) {
		super();
		this.productCategoryId = productCategoryId;
		this.name = name;
		this.applicableTax = applicableTax;
	}

	public Integer getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(Integer productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getApplicableTax() {
		return applicableTax;
	}

	public void setApplicableTax(Double applicableTax) {
		this.applicableTax = applicableTax;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productCategoryId == null) ? 0 : productCategoryId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductCategory other = (ProductCategory) obj;
		if (productCategoryId == null) {
			if (other.productCategoryId != null)
				return false;
		} else if (!productCategoryId.equals(other.productCategoryId))
			return false;
		return true;
	}

}
