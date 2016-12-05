package com.example.benson.foodpacker2;

/**
 * Created by Benson on 2016/11/11.
 */
public class GetDataAdapter {
    String id;
    String name;
    String phone_number;
    String subject;
    String menu_page2;

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id = id;
    }


    public String getPhone_number() {

        return phone_number;
    }

    public void setPhone_number(String phone_number1) {

        this.phone_number = phone_number1;
    }

    public String getSubject() {

        return subject;
    }

    public void setSubject(String subject1) {

        this.subject = subject1;
    }

    public void setMenu_page2(String menu_page2){
        this.menu_page2=menu_page2;
    }
    public String getMenu_page2(){
        return menu_page2;
    }

}
