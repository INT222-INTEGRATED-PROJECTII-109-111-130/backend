package IntegratedProject.int222.models;

import org.hibernate.internal.build.AllowPrintStacktrace;
import org.springframework.web.bind.annotation.PutMapping;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Size {
  @Id
  public long sizeId;
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
