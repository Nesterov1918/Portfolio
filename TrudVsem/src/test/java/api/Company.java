package api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Company {

    @JsonProperty("companycode")
    private String companycode;

    @JsonProperty("hr-agency")
    private boolean hrAgency;

    @JsonProperty("inn")
    private String inn;

    @JsonProperty("kpp")
    private String kpp;

    @JsonProperty("name")
    private String name;

    @JsonProperty("ogrn")
    private String ogrn;

    @JsonProperty("site")
    private String site;

    @JsonProperty("url")
    private String url;

    public Company() {

    }

    public Company(String companycode, boolean hrAgency, String inn, String kpp, String name, String ogrn, String site, String url) {
        this.companycode = companycode;
        this.hrAgency = hrAgency;
        this.inn = inn;
        this.kpp = kpp;
        this.name = name;
        this.ogrn = ogrn;
        this.site = site;
        this.url = url;
    }

    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    public boolean hrAgency() {
        return hrAgency;
    }

    public void setHr_agency(boolean hrAgency) {
        this.hrAgency = hrAgency;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOgrn() {
        return ogrn;
    }

    public void setOgrn(String ogrn) {
        this.ogrn = ogrn;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    @Override
    public String toString() {
        return "Company{" +
                "companycode='" + companycode + '\'' +
                ", hrAgency=" + hrAgency +
                ", inn='" + inn + '\'' +
                ", kpp='" + kpp + '\'' +
                ", name='" + name + '\'' +
                ", ogrn='" + ogrn + '\'' +
                ", site='" + site + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
