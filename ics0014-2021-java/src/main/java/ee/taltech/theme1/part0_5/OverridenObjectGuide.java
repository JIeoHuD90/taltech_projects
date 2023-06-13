package ee.taltech.theme1.part0_5;

import java.util.Objects;

public class OverridenObjectGuide {

    public String name;

    public OverridenObjectGuide(String name) {
        this.name = name;
    }

    //code -> generate (learn shortcut)
    //override methods

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OverridenObjectGuide{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OverridenObjectGuide that = (OverridenObjectGuide) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
