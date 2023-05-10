package CommandsManagement;

import java.io.*;

public enum CommandsEnum implements Serializable {
    /*EXECUTE_SCRIPT("execute_script"), */ADD("add"), ADD_IF_MAX("add_if_max"),
    FILTER_BY_EVENT("filter_by_event"), CLEAR("clear"), FILTER_CONTAINS_NAME("filter_contains_name"),
    HELP("help"), INFO("info"), PRINT_FIELD_ASCENDING_PRICE("print_field_ascending_price"), UPDATE_ID("update_id"),
    SHOW("show"), REMOVE_HEAD("remove_head"), REMOVE_FIRST("remove_first"), REMOVE_BY_ID("remove_by_id"),
    EXIT("exit"), RETURN_ID("-");

    private String name;

    CommandsEnum(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
