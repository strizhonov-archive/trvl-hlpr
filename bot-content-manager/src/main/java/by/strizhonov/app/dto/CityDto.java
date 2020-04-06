package by.strizhonov.app.dto;

import java.util.Objects;

public class CityDto {

    private long id;

    private String name;

    private String description;

    public CityDto() {
        // Empty bean constructor
    }

    public CityDto(final long id, final String name, final String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final CityDto cityDto = (CityDto) o;
        return id == cityDto.id &&
                Objects.equals(name, cityDto.name) &&
                Objects.equals(description, cityDto.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }

    @Override
    public String toString() {
        return "CityDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
