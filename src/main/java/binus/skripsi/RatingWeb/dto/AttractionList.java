package binus.skripsi.RatingWeb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttractionList {
    private String name;
    private String address;
    private String website;
    private String photoPath;
}
