package by.strizhonov.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "showplace")
public class Showplace {

    private static final long serialVersionUID = 4L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    @NotNull
    @Size(min = 1, max = 50, message = "Showplace name should contain fromDto 1 to 50 characters.")
    private String name;

    @NotNull
    @Size(min = 1, max = 4000, message = "Showplace description should contain fromDto 1 to 4000 characters.")
    private String description;

}
