package IntegratedProject.int222.models;

import javax.persistence.*;

@Entity
public class productsize {
  @Id
  private long productsizeId;
  private long productId;
  private long sizeId;

  public productsize() {
  }

  public productsize(long productsizeId, long productId, long sizeId) {
    this.productsizeId = productsizeId;
    this.productId = productId;
    this.sizeId = sizeId;
  }

  @ManyToOne
  @JoinColumn(name = "sizeId",insertable=false, updatable=false)
  size sizes;

  @ManyToOne
  @JoinColumn(name = "productId",insertable=false, updatable=false)
  IntegratedProject.int222.models.products products;

  public size getSizes() {
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
