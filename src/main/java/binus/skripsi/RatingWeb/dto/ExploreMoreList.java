package binus.skripsi.RatingWeb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExploreMoreList {
	private long id;
    private String name;
    private String address;
    private String photoName;
    private String photoPath;
}
