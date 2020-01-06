package framework;

import java.util.HashMap;

public class ApiObjectMethodModel {

    public HashMap<String,Object> query;
    public HashMap<String,Object> header;
    public HashMap<String,Object> postBody;
    public String postBodyRaw;
    public String method="get";
    public String url = "";

}
