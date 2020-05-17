package by.strizhonov.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@EqualsAndHashCode(exclude = "city")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "showplace")
public class Showplace {

    private static final long serialVersionUID = 4L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @Column(unique = true)
    @NotNull
    @Size(min = 1, max = 50, message = "Showplace name should contain fromDto 1 to 50 characters.")
    private String name;

    @NotNull
    @Size(min = 1, max = 4000, message = "Showplace description should contain fromDto 1 to 4000 characters.")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CITY_ID")
    private City city;

}
