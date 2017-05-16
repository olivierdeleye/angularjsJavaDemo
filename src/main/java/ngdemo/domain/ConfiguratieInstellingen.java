/*
 * Class die alle configuratieinstellingen uit DB leest. Singleton Context wordt gevoed met data en op applicatieScope beschikbaar gezet.
 */
package ngdemo.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author olivier deleye
 */

@Entity
@Table(name = "configuratieinstellingen")
public class ConfiguratieInstellingen implements Serializable{

    private static final long serialVersionUID = 1L;
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String scan_COM_port;
    @Column
    private String continueScan_COM_port;
    @Column
    private String path_to_directory_bestanden;
    @Column
    private int timeOutValue;
    @Column
    private String paswoord;
    @Column
    private String mailServer;
    @Column
    private String emailFrom;
    @Column
    private String emailTo1;
    @Column
    private String emailTo2;
    @Column
    private String emailTo3;
    
    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     */
    public ConfiguratieInstellingen() {
    }
    
    /**
     *
     * @param default_COM_port
     * @param path_to_directory_bestanden
     * @param timeOutValue
     */
    public ConfiguratieInstellingen(String scan_COM_port, String path_to_directory_bestanden, int timeOutValue) {
        this.scan_COM_port = scan_COM_port; 
        this.path_to_directory_bestanden = path_to_directory_bestanden;
        this.timeOutValue = timeOutValue;
    }

    // GETTERS SETTERS

    /**
     *
     * @return
     */
    public String getScan_COM_port() {
        return scan_COM_port;
    }

    /**
     *
     * @param scan_COM_port
     */
    public void setScan_COM_port(String scan_COM_port) {
        this.scan_COM_port = scan_COM_port;
    }

    public String getContinueScan_COM_port() {
        return continueScan_COM_port;
    }

    public void setContinueScan_COM_port(String continueScan_COM_port) {
        this.continueScan_COM_port = continueScan_COM_port;
    }

    public String getPath_to_directory_bestanden() {
        return path_to_directory_bestanden;
    }

    public void setPath_to_directory_bestanden(String path_to_directory_bestanden) {
        this.path_to_directory_bestanden = path_to_directory_bestanden;
    }
  
    
    
    /**
     *
     * @return
     */
    public String getPathToDirectoryBestanden(){
        return path_to_directory_bestanden;
    }
    
    /**
     *
     * @param path
     */
    public void setPathToDirectoryBestanden(String path){
        path_to_directory_bestanden = path;
    }

    /**
     *
     * @return
     */
    public int getTimeOutValue() {
        return timeOutValue;
    }

    /**
     *
     * @param timeOutValue
     */
    public void setTimeOutValue(int timeOutValue) {
        this.timeOutValue = timeOutValue;
    }

    /**
     *
     * @return
     */
    public String getPaswoord() {
        return paswoord;
    }

    /**
     *
     * @param paswoord
     */
    public void setPaswoord(String paswoord) {
        this.paswoord = paswoord;
    }

    /**
     *
     * @return
     */
    public String getMailServer() {
        return mailServer;
    }

    /**
     *
     * @param mailServer
     */
    public void setMailServer(String mailServer) {
        this.mailServer = mailServer;
    }

    /**
     *
     * @return
     */
    public String getEmailFrom() {
        return emailFrom;
    }

    /**
     *
     * @param emailFrom
     */
    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    /**
     *
     * @return
     */
    public String getEmailTo1() {
        return emailTo1;
    }

    /**
     *
     * @param emailTo1
     */
    public void setEmailTo1(String emailTo1) {
        this.emailTo1 = emailTo1;
    }

    /**
     *
     * @return
     */
    public String getEmailTo2() {
        return emailTo2;
    }

    /**
     *
     * @param emailTo2
     */
    public void setEmailTo2(String emailTo2) {
        this.emailTo2 = emailTo2;
    }

    /**
     *
     * @return
     */
    public String getEmailTo3() {
        return emailTo3;
    }

    /**
     *
     * @param emailTo3
     */
    public void setEmailTo3(String emailTo3) {
        this.emailTo3 = emailTo3;
    }
    
    
    
    
    // HASH EQUALS TOSTRING
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.id;
        hash = 83 * hash + Objects.hashCode(this.scan_COM_port);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ConfiguratieInstellingen other = (ConfiguratieInstellingen) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.scan_COM_port, other.scan_COM_port)) {
            return false;
        }
        return true;
    }

    
    //################################################################## PRIVATE METHODS ##########################################################################################

    @Override
    public String toString() {
        return "ConfiguratieInstellingen{" + "id=" + id + ", scan_COM_port=" + scan_COM_port + ", continueScan_COM_port=" + continueScan_COM_port + ", path_to_directory_bestanden=" + path_to_directory_bestanden + ", timeOutValue=" + timeOutValue + ", paswoord=" + paswoord + ", mailServer=" + mailServer + ", emailFrom=" + emailFrom + ", emailTo1=" + emailTo1 + ", emailTo2=" + emailTo2 + ", emailTo3=" + emailTo3 + '}';
    }

    
}
