
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by dujunfei on 2017/6/2.
 */
public class Paper {
    private int ID;
    private String DOI;
    private Set<String> citationDOI;
    private String author;
    private String organization;
    private List<String> orgList= new ArrayList<>();
    private int year;

    public Paper() {
        DOI = "";
        author = "";
        organization = "";
        orgList = new ArrayList<>();
    }

    public Paper(JSONObject jsonObject, int index) throws JSONException {
        ID = index;//就是一个计数的id
        citationDOI = new HashSet<>();
        if (jsonObject.has("CR")) {
            String[] citedLines = jsonObject.get("CR").toString().split("\\|\\|");

            for (String line : citedLines) {
                String[] lineSplit = line.split("DOI ");
                if (lineSplit.length >= 2 && !lineSplit[lineSplit.length - 1].equals("")) {
                    citationDOI.add(lineSplit[lineSplit.length - 1]);
                }
            }
        }
        if (!jsonObject.isNull("DI")) {
            DOI = jsonObject.get("DI").toString();
        }
        if (!jsonObject.isNull("PY")) {
            year = Integer.parseInt(jsonObject.get("PY").toString());
        }
        if (jsonObject.has("AF")) {
            String[] citedLines = jsonObject.get("AF").toString().split("\\|\\|");
            author = citedLines[0];
        }
        if (jsonObject.has("C1")) {
            String[] citedLines = jsonObject.get("C1").toString().split("\\|\\|");
            for (String line : citedLines) {
                String[] stmp = line.split("]");
                if (stmp.length >= 2) {
                    String[] stmp1 = stmp[1].split(",");
                    organization = stmp1[0].trim();
                    organization = DataProcess.getStemOrganization(organization);
                    if (organization!=null&&!orgList.contains(organization)){
                        orgList.add(organization);
                    }

                }
            }
        }
    }


    public int getID() {
        return ID;
    }

    public Set<String> getCitationDOI() {
        return citationDOI;
    }

    public String getDOI() {
        return DOI;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getOrganization() {
        return organization;
    }

    public String getAuthor() {
        return author;
    }

    public List<String> getOrgList() {
        return orgList;
    }

    public int getYear() {
        return year;
    }
}
