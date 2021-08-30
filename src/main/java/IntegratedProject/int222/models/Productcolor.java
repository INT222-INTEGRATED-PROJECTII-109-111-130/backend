package IntegratedProject.int222.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Productcolor {
  @Id
  private long productcolorId;
  private long productId;
  private long colorId;

  public Productcolor() {

  }

  public Productcolor(long productcolorId, long productId, long colorId) {
    this.productcolorId = productcolorId;
    this.productId = productId;
    this.colorId = colorId;
  }

  @ManyToOne
  @JoinColumn(name = "colorId", insertable=false, updatable=false)
  Colors colors;

  @ManyToOne
  @JoinColumn(name = "productId",insertable=false, updatable=false)
  Products products;

  public Colors getColors() {
    return colors;
  }

  public long getProductcolorId() {
    return productcolorId;
  }

  public void setProductcolorId(long productcolorId) {
    this.productcolorId = productcolorId;
  }


  public long getProductId() {
    return productId;
  }

  public void setProductId(long productId) {
    this.productId = productId;
  }


  public long getColorId() {
    return colorId;
  }

  public void setColorId(long colorId) {
    this.colorId = colorId;
  }

}
