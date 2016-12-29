package stepic_web_server.dataSets;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class UsersDataSet implements Serializable {
    private static final long serialVersionUID = -8706689714326132798L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", unique = true, updatable = false)
    private String name;

    @Column(name = "password")
    private String password;

    public UsersDataSet() {
    }

    public UsersDataSet(long id, String name) {
        setId(id);
        setName(name);
    }

    public UsersDataSet(String name) {
        setId(-1);
        setName(name);
        setPassword(name);
    }

    public UsersDataSet(String name, String password) {
        setId(-1);
        setName(name);
        setPassword(password);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersDataSet dataSet = (UsersDataSet) o;

        if (name != null ? !name.equals(dataSet.name) : dataSet.name != null) return false;
        return password != null ? password.equals(dataSet.password) : dataSet.password == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UsersDataSet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
