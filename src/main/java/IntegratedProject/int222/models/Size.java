package IntegratedProject.int222.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Size {
  @Id
  private long sizeId;
  private String sizeValue;

  @OneToMany(mappedBy = "sizes")
  Set<Productsize> productcsizes;

  public long getSizeId() {
    return sizeId;
  }

  public void setSizeId(long sizeId) {
    this.sizeId = sizeId;
  }


  public String getSizeValue() {
    return sizeValue;
  }

  public void setSizeValue(String sizeValue) {
    this.sizeValue = sizeValue;
  }

}
