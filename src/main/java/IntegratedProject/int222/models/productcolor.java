package IntegratedProject.int222.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class productcolor {
  @Id
  private long productcolorId;
  private long productId;
  private long colorId;

  public productcolor() {

  }

  public productcolor(long productcolorId, long productId, long colorId) {
    this.productcolorId = productcolorId;
    this.productId = productId;
    this.colorId = colorId;
  }

  @ManyToOne
  @JoinColumn(name = "colorId", insertable=false, updatable=false)
  IntegratedProject.int222.models.colors colors;

  @ManyToOne
  @JoinColumn(name = "productId",insertable=false, updatable=false)
  IntegratedProject.int222.models.products products;

  public IntegratedProject.int222.models.colors getColors() {
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
