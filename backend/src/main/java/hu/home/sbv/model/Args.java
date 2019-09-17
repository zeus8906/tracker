package hu.home.sbv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "args")
public class Args {

    @Id
    @Column(name = "key")
    private String key;

    @Column(name = "value")
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Args{" +
                "key='" + key + '\'' +
                ", value=" + value +
                '}';
    }
}
