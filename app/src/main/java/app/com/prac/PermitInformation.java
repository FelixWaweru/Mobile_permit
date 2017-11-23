package app.com.prac;

import java.io.Serializable;

public class PermitInformation implements Serializable {
    public String Name;
    public String Title;
    public String Number;
    public String CompanyName;
    public String CompanyAddress;
    public String Person;
    public String JbTitle;
    public String Date;
    public String Crew;
    public String Cast;
    public String Start;
    public String End;
    public String Loc;


    public PermitInformation(String name, String title, String number, String companyName, String companyAddress, String person, String jbTitle, String date, String crew, String cast, String start, String end, String loc) {
        Name = name;
        Title = title;
        Number = number;
        CompanyName = companyName;
        CompanyAddress = companyAddress;
        Person = person;
        JbTitle = jbTitle;
        Date = date;
        Crew = crew;
        Cast = cast;
        Start = start;
        End = end;
        Loc = loc;
    }

} 