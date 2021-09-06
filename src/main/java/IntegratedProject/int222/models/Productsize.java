package IntegratedProject.int222.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Productsize {
  @Id
  private long productsizeId;
  private long productId;
  private long sizeId;

  public Productsize() {
  }

  public Productsize(long productsizeId, long productId, long sizeId) {
    this.productsizeId = productsizeId;
    this.productId = productId;
    this.sizeId = sizeId;
  }

  @ManyToOne
  @JoinColumn(name = "sizeId", insertable=false, updatable=false)
  Size sizes;

  @ManyToOne
  @JoinColumn(name = "productId",insertable=false, updatable=false)
  Products products;

  public Size getSizes() {
    return sizes;
  }

  public long getProductsizeId() {
    return productsizeId;
  }

  public void setProductsizeId(long productsizeId) {
    this.productsizeId = productsizeId;
  }


  public long getProductId() {
    return productId;
  }

  public void setProductId(long productId) {
    this.productId = productId;
  }


  public long getSizeId() {
    return sizeId;
  }

  public void setSizeId(long sizeId) {
    this.sizeId = sizeId;
  }

}
