package IntegratedProject.int222.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class colors {
  @Id
  private long colorId;
  private String colorName;
  private String colorValue;

  @OneToMany(mappedBy = "colors")
  Set<productcolor> productcolors;

  public long getColorId() {
    return colorId;
  }

  public void setColorId(long colorId) {
    this.colorId = colorId;
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

}
