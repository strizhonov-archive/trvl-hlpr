package by.strizhonov.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowplaceDto {

    private long id;

    @NotNull
    private String name;

    @NotNull
    private String description;

}
