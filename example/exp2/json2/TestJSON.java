package com.example.exp2.json2;

public class TestJSON {

    public static void main(String[] args) {
        Space bj = new Space(1, "北京");
        String jsonStr = ObjectMapper.toJSON(bj);
        System.out.println(jsonStr);

        String jsonSTr2 = "{\"id\":2,\"name\":\"上海\"}";
        //反射，space、class其实就是用将来的“注入”
        Space sh = ObjectMapper.fromJSON(jsonSTr2, Space.class);
        System.out.println(sh);
    }
}
