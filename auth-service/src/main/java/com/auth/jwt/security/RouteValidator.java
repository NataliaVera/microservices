package com.auth.jwt.security;

import com.auth.jwt.dto.RequestDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

@Component
@ConfigurationProperties(prefix = "admin-paths")
public class RouteValidator {

    private List<RequestDto> paths;

    public List<RequestDto> getPaths() {
        return paths;
    }

    public void setPaths(List<RequestDto> paths) {
        this.paths = paths;
    }

    //Se encarga de validar
    public boolean isAdmin(RequestDto requestDto){
        return paths.stream().anyMatch(path ->
                Pattern.matches(path.getUri(), requestDto.getUri()) && path.getMethod().equals(requestDto.getMethod()));
    }
}
