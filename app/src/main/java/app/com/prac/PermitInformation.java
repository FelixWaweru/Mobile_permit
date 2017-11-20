package app.com.prac;

public class PermitInformation {
    String Name;
    String Title;
    String Number;
    String CompanyName;
    String CompanyAddress;
    String Person;
    String JbTitle;
    String Date;
    String Email;
    String Crew;
    String Cast;
    String Start;
    String End;
    String Loc;

    public PermitInformation(String name, String title, String number, String companyName, String companyAddress, String person, String jbTitle, String date, String email, String crew, String cast, String start, String end, String loc) {
        Name = name;
        Title = title;
        Number = number;
        CompanyName = companyName;
        CompanyAddress = companyAddress;
        Person = person;
        JbTitle = jbTitle;
        Date = date;
        Email = email;
        Crew = crew;
        Cast = cast;
        Start = start;
        End = end;
        Loc = loc;
    }

    public PermitInformation()
    {

    }
} 