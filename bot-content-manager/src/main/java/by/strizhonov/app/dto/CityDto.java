package by.strizhonov.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityDto {

    @Min(0)
    private long id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    private List<@Valid ShowplaceDto> showplaces;

}
