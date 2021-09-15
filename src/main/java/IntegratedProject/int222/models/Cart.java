package IntegratedProject.int222.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Cart {
  @Id
  private long cartId;
  private long accountId;
  private long productId;

  @ManyToOne
  @JoinColumn(name = "productId",insertable=false, updatable=false)
  Products products;

  @ManyToOne
  @JoinColumn(name = "accountId",insertable=false, updatable=false)
  Accounts accounts;

  public long getCartId() {
    return cartId;
  }

  public void setCartId(long cartId) {
    this.cartId = cartId;
  }

  public Products getProducts() {
    return products;
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

}
