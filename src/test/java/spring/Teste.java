package spring;

public class Teste {

    private int id;
    private String name;

    public Teste() {
    }

    public Teste(int id) {
        this.id = id;
    }

    public Teste(String name) {
        this.name = name;
    }

    public Teste(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    @Override public String toString() {
	return "Teste{" +
			"id=" + id +
			", name='" + name + '\'' +
			'}';
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Teste teste = (Teste)o;

        if (id != teste.id)
            return false;
        return !(name != null ? !name.equals(teste.name) : teste.name != null);

    }

    @Override public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
