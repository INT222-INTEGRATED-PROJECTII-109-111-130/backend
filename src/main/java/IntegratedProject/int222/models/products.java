package IntegratedProject.int222.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class products {
  @Id
  private long productId;
  private String productName;
  private String productDescription;
  private java.sql.Date onsaleDate;
  private double productPrice;
  private String productImage;
  private long brandId;

  @ManyToOne
  @JoinColumn(name = "brandId",insertable=false, updatable=false)
  IntegratedProject.int222.models.brands brands;

  @OneToMany(mappedBy = "products")
  List<productcolor> productcolors;

  @OneToMany(mappedBy = "products")
  List<cart> carts;

  @OneToMany(mappedBy = "products")
  List<productsize> productsizes;

  public IntegratedProject.int222.models.brands getBrands() {
    return brands;
  }

  public List<productcolor> getProductcolors() {
    return productcolors;
  }

  public long getProductId() {
    return productId;
  }

  public void setProductId(long productId) {
    this.productId = productId;
  }


  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }


  public String getProductDescription() {
    return productDescription;
  }

  public void setProductDescription(String productDescription) {
    this.productDescription = productDescription;
  }


  public java.sql.Date getOnsaleDate() {
    return onsaleDate;
  }

  public void setOnsaleDate(java.sql.Date onsaleDate) {
    this.onsaleDate = onsaleDate;
  }


  public double getProductPrice() {
    return productPrice;
  }

  public void setProductPrice(double productPrice) {
    this.productPrice = productPrice;
  }


  public String getProductImage() {
    return productImage;
  }

  public void setProductImage(String productImage) {
    this.productImage = productImage;
  }


  public long getBrandId() {
    return brandId;
  }

  public void setBrandId(long brandId) {
    this.brandId = brandId;
  }

  public List<productsize> getProductsizes() {
    return productsizes;
  }


}
