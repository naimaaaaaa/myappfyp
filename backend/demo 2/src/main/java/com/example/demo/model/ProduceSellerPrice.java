// package com.example.demo.model;

// import java.io.Serializable;

// import javax.persistence.Entity;
// import javax.persistence.EntityListeners;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;
// import javax.persistence.MapsId;
// import javax.persistence.Table;
// import java.math.BigDecimal;

// import org.springframework.data.jpa.domain.support.AuditingEntityListener;

// import com.example.demo.UserType;

// //Let's create a simple User class
// @Entity
// @Table(name = "PSPrices")
// @EntityListeners(AuditingEntityListener.class)

// public class ProduceSellerPrice implements Serializable {
// 	private static final long serialVersionUID = 1L;
	
// 	@Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
// 	Long id;
	
// 	 @ManyToOne
// 	 @JoinColumn(name = "produceId")
// 	 Produce produce;

// 	 @ManyToOne
// 	 @JoinColumn(name = "sellerId")
// 	 User seller;

// 	 BigDecimal price;
	 
	 

// 	public ProduceSellerPrice() {
// 		super();
// 		// TODO Auto-generated constructor stub
// 	}

// 	public ProduceSellerPrice(Produce produce, User seller, BigDecimal price) {
// 		this.produce = produce;
// 		this.seller = seller;
// 		this.price = price;
// 	}

// 	public Produce getProduce() {
// 		return produce;
// 	}

// 	public void setProduce(Produce produce) {
// 		this.produce = produce;
// 	}

// 	public User getSeller() {
// 		return seller;
// 	}

// 	public void setSeller(User seller) {
// 		this.seller = seller;
// 	}

// 	public BigDecimal getPrice() {
// 		return price;
// 	}

// 	public void setPrice(BigDecimal price) {
// 		this.price = price;
// 	}

// 	@Override
// 	public String toString() {
// 		return "ProduceSellerPrice [id=" + id + ", produce=" + produce + ", seller=" + seller + ", price=" + price
// 				+ "]";
// 	}
	 
	
	 

// }
