package com.example.myapplication.docfragment;

public class Historyproject {

    int historyid;
    String historyname;
    int historyage;
    String historysex;
    String historyitemname;
    String historydoc;
    String historystarttime;
    String historycharge;
    String historyknowsitu;
    public int getHistoryid() {
        return historyid;
    }

    public String getHistorycharge() {
        return historycharge;
    }

    public String getHistoryknowsitu() {
        return historyknowsitu;
    }

    public String getHistoryname() {
        return historyname;
    }

    public int getHistoryage() {
        return historyage;
    }

    public String getHistorysex() {
        return historysex;
    }

    public String getHistoryitemname() {
        return historyitemname;
    }

    public String getHistorydoc() {
        return historydoc;
    }

    public String getHistorystarttime() {
        return historystarttime;
    }
    public Historyproject(int history_id,String history_name,int history_age ,String history_sex,String history_itemname,String history_doc,String history_starttime,String history_charge,String history_knowsitu){
        this.historyid = history_id;
        this.historycharge = history_charge;
        this.historyknowsitu = history_knowsitu;
        this.historyname = history_name;
        this.historyage = history_age;
        this.historysex = history_sex;
        this.historyitemname = history_itemname;
        this.historydoc = history_doc;
        this.historystarttime = history_starttime;
    }


}
