package IntegratedProject.int222.models;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private final String id;

    public JwtResponse(String jwttoken,Long id) {
        this.jwttoken = jwttoken;
        this.id = Long.toString(id) ;
    }

    public String getToken() {
        return this.jwttoken;
    }

    public String getId() {
        return this.id;
    }
}