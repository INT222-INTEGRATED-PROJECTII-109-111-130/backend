package IntegratedProject.int222.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@Entity
public class Brands {
  @Id
  private long brandId;
  private String brandName;

  @OneToMany(mappedBy = "brands")
  List<Products> products;


  public long getBrandId() {
    return brandId;
  }

  public void setBrandId(long brandId) {
    this.brandId = brandId;
  }


  public String getBrandName() {
    return brandName;
  }

  public void setBrandName(String brandName) {
    this.brandName = brandName;
  }

}
