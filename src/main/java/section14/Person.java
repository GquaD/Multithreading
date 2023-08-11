package section14;

import java.io.Serializable;

public class Person implements Serializable {
    private static final long serialVersionUID = 1;
    private int personId;

    public Person(int personId) {
        this.personId = personId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
}
