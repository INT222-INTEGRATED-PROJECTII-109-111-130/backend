package IntegratedProject.int222.models;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class cart {
  @Id
  private long cartId;
  private long accountId;
  private long productId;
  private String productName;
  private String productDescription;
  private double productPrice;
  private String productImage;
  private String sizeValue;
  private String brandName;
  private String colorName;
  private String colorValue;
  private long quantity;

  @ManyToOne
  @JoinColumn(name = "productId",insertable=false, updatable=false)
  IntegratedProject.int222.models.products products;

  @ManyToOne
  @JoinColumn(name = "accountId",insertable=false, updatable=false)
  IntegratedProject.int222.models.accounts accounts;

  public long getCartId() {
    return cartId;
  }

  public void setCartId(long cartId) {
    this.cartId = cartId;
  }


  public long getAccountId() {
    return accountId;
  }

  public void setAccountId(long accountId) {
    this.accountId = accountId;
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


  public String getSizeValue() {
    return sizeValue;
  }

  public void setSizeValue(String sizeValue) {
    this.sizeValue = sizeValue;
  }


  public String getBrandName() {
    return brandName;
  }

  public void setBrandName(String brandName) {
    this.brandName = brandName;
  }


  public String getColorName() {
    return colorName;
  }

  public void setColorName(String colorName) {
    this.colorName = colorName;
  }


  public String getColorValue() {
    return colorValue;
  }

  public void setColorValue(String colorValue) {
    this.colorValue = colorValue;
  }


  public long getQuantity() {
    return quantity;
  }

  public void setQuantity(long quantity) {
    this.quantity = quantity;
  }

}
