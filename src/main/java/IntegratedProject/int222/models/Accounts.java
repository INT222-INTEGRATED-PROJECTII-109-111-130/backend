package IntegratedProject.int222.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Accounts {
  @Id
  private long accountId;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private String accountRole;

  @OneToMany(mappedBy = "accounts")
  List<Cart> carts;

  public List<Cart> getCarts() {
    return carts;
  }

  public long getAccountId() {
    return accountId;
  }

  public void setAccountId(long accountId) {
    this.accountId = accountId;
  }


  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }


  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getAccountRole() {
    return accountRole;
  }

  public void setAccountRole(String accountRole) {
    this.accountRole = accountRole;
  }

}
