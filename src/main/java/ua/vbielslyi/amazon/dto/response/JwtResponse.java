package ua.vbielslyi.amazon.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.vbielslyi.amazon.util.ObjectUtil;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String token;

    @Override
    public String toString() {
        return ObjectUtil.toJson(this);
    }
}
