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

    public UsersDataSet() {
    }

    public UsersDataSet(long id, String name) {
        setId(id);
        setName(name);
    }

    public UsersDataSet(String name) {
        setId(-1);
        setName(name);
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("UsersDataSet{id=%d, name='%s'}", id, name);
    }
}
