//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.08.17 at 03:19:13 PM BST 
//


package org.crossref.qrschema._3;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.crossref.org/qrschema/3.0}doi"/>
 *         &lt;element ref="{http://www.crossref.org/qrschema/3.0}msg" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;sequence>
 *             &lt;element ref="{http://www.crossref.org/qrschema/3.0}issn" maxOccurs="2" minOccurs="0"/>
 *             &lt;element ref="{http://www.crossref.org/qrschema/3.0}isbn" maxOccurs="6" minOccurs="0"/>
 *             &lt;element ref="{http://www.crossref.org/qrschema/3.0}series_title" minOccurs="0"/>
 *             &lt;element ref="{http://www.crossref.org/qrschema/3.0}journal_title" minOccurs="0"/>
 *             &lt;element ref="{http://www.crossref.org/qrschema/3.0}volume_title" minOccurs="0"/>
 *             &lt;choice>
 *               &lt;element ref="{http://www.crossref.org/qrschema/3.0}author" minOccurs="0"/>
 *               &lt;element ref="{http://www.crossref.org/qrschema/3.0}contributors" minOccurs="0"/>
 *             &lt;/choice>
 *             &lt;element ref="{http://www.crossref.org/qrschema/3.0}volume" minOccurs="0"/>
 *             &lt;element ref="{http://www.crossref.org/qrschema/3.0}issue" minOccurs="0"/>
 *             &lt;element ref="{http://www.crossref.org/qrschema/3.0}first_page" minOccurs="0"/>
 *             &lt;element ref="{http://www.crossref.org/qrschema/3.0}item_number" minOccurs="0"/>
 *             &lt;element ref="{http://www.crossref.org/qrschema/3.0}last_page" minOccurs="0"/>
 *             &lt;element ref="{http://www.crossref.org/qrschema/3.0}edition_number" minOccurs="0"/>
 *             &lt;element ref="{http://www.crossref.org/qrschema/3.0}component_number" minOccurs="0"/>
 *             &lt;element ref="{http://www.crossref.org/qrschema/3.0}year" maxOccurs="6" minOccurs="0"/>
 *             &lt;element ref="{http://www.crossref.org/qrschema/3.0}publication_type" minOccurs="0"/>
 *             &lt;element ref="{http://www.crossref.org/qrschema/3.0}article_title" minOccurs="0"/>
 *             &lt;element ref="{http://www.crossref.org/qrschema/3.0}institution_name" minOccurs="0"/>
 *             &lt;element ref="{http://www.crossref.org/qrschema/3.0}identifier" maxOccurs="unbounded" minOccurs="0"/>
 *             &lt;element ref="{http://www.crossref.org/qrschema/3.0}component_list" minOccurs="0"/>
 *           &lt;/sequence>
 *           &lt;sequence>
 *             &lt;element ref="{http://www.crossref.org/qrschema/3.0}crm-item" maxOccurs="unbounded"/>
 *             &lt;element ref="{http://www.crossref.org/qrschema/3.0}doi_record" minOccurs="0"/>
 *           &lt;/sequence>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="key">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="128"/>
 *             &lt;minLength value="0"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="enable-multiple-hits" default="false">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}boolean">
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="forward-match" default="false">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}boolean">
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="status">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *             &lt;enumeration value="resolved"/>
 *             &lt;enumeration value="unresolved"/>
 *             &lt;enumeration value="multiresolved"/>
 *             &lt;enumeration value="malformed"/>
 *             &lt;enumeration value="none"/>
 *             &lt;enumeration value="stored-query-created"/>
 *             &lt;enumeration value="system-too-busy"/>
 *             &lt;enumeration value="system-unavailable"/>
 *             &lt;enumeration value="system-error"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="fl_count">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="query_mode">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *             &lt;enumeration value="metadata"/>
 *             &lt;enumeration value="author-title"/>
 *             &lt;enumeration value="formatted-citation"/>
 *             &lt;enumeration value="refxpress"/>
 *             &lt;enumeration value="doi"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "doi",
    "msg",
    "issn",
    "isbn",
    "seriesTitle",
    "journalTitle",
    "volumeTitle",
    "author",
    "contributors",
    "volume",
    "issue",
    "firstPage",
    "itemNumber",
    "lastPage",
    "editionNumber",
    "componentNumber",
    "year",
    "publicationType",
    "articleTitle",
    "institutionName",
    "identifier",
    "componentList",
    "crmItem",
    "doiRecord"
})
@XmlRootElement(name = "query")
public class Query {

    @XmlElement(required = true)
    protected Doi doi;
    protected String msg;
    protected List<Issn> issn;
    protected List<Isbn> isbn;
    @XmlElement(name = "series_title")
    protected SeriesTitle seriesTitle;
    @XmlElement(name = "journal_title")
    protected JournalTitle journalTitle;
    @XmlElement(name = "volume_title")
    protected VolumeTitle volumeTitle;
    protected Author author;
    protected Contributors contributors;
    protected Volume volume;
    protected Issue issue;
    @XmlElement(name = "first_page")
    protected FirstPage firstPage;
    @XmlElement(name = "item_number")
    protected ItemNumber itemNumber;
    @XmlElement(name = "last_page")
    protected LastPage lastPage;
    @XmlElement(name = "edition_number")
    protected EditionNumber editionNumber;
    @XmlElement(name = "component_number")
    protected ComponentNumber componentNumber;
    protected List<Year> year;
    @XmlElement(name = "publication_type")
    protected PublicationType publicationType;
    @XmlElement(name = "article_title")
    protected ArticleTitle articleTitle;
    @XmlElement(name = "institution_name")
    protected InstitutionName institutionName;
    protected List<Identifier> identifier;
    @XmlElement(name = "component_list")
    protected ComponentList componentList;
    @XmlElement(name = "crm-item")
    protected List<CrmItem> crmItem;
    @XmlElement(name = "doi_record")
    protected DoiRecord doiRecord;
    @XmlAttribute(name = "key")
    protected String key;
    @XmlAttribute(name = "enable-multiple-hits")
    protected Boolean enableMultipleHits;
    @XmlAttribute(name = "forward-match")
    protected Boolean forwardMatch;
    @XmlAttribute(name = "status")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String status;
    @XmlAttribute(name = "fl_count")
    protected BigInteger flCount;
    @XmlAttribute(name = "query_mode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String queryMode;

    /**
     * Gets the value of the doi property.
     * 
     * @return
     *     possible object is
     *     {@link Doi }
     *     
     */
    public Doi getDoi() {
        return doi;
    }

    /**
     * Sets the value of the doi property.
     * 
     * @param value
     *     allowed object is
     *     {@link Doi }
     *     
     */
    public void setDoi(Doi value) {
        this.doi = value;
    }

    /**
     * Gets the value of the msg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsg() {
        return msg;
    }

    /**
     * Sets the value of the msg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsg(String value) {
        this.msg = value;
    }

    /**
     * Gets the value of the issn property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the issn property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIssn().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Issn }
     * 
     * 
     */
    public List<Issn> getIssn() {
        if (issn == null) {
            issn = new ArrayList<Issn>();
        }
        return this.issn;
    }

    /**
     * Gets the value of the isbn property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the isbn property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIsbn().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Isbn }
     * 
     * 
     */
    public List<Isbn> getIsbn() {
        if (isbn == null) {
            isbn = new ArrayList<Isbn>();
        }
        return this.isbn;
    }

    /**
     * Gets the value of the seriesTitle property.
     * 
     * @return
     *     possible object is
     *     {@link SeriesTitle }
     *     
     */
    public SeriesTitle getSeriesTitle() {
        return seriesTitle;
    }

    /**
     * Sets the value of the seriesTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link SeriesTitle }
     *     
     */
    public void setSeriesTitle(SeriesTitle value) {
        this.seriesTitle = value;
    }

    /**
     * Gets the value of the journalTitle property.
     * 
     * @return
     *     possible object is
     *     {@link JournalTitle }
     *     
     */
    public JournalTitle getJournalTitle() {
        return journalTitle;
    }

    /**
     * Sets the value of the journalTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link JournalTitle }
     *     
     */
    public void setJournalTitle(JournalTitle value) {
        this.journalTitle = value;
    }

    /**
     * Gets the value of the volumeTitle property.
     * 
     * @return
     *     possible object is
     *     {@link VolumeTitle }
     *     
     */
    public VolumeTitle getVolumeTitle() {
        return volumeTitle;
    }

    /**
     * Sets the value of the volumeTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link VolumeTitle }
     *     
     */
    public void setVolumeTitle(VolumeTitle value) {
        this.volumeTitle = value;
    }

    /**
     * Gets the value of the author property.
     * 
     * @return
     *     possible object is
     *     {@link Author }
     *     
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * Sets the value of the author property.
     * 
     * @param value
     *     allowed object is
     *     {@link Author }
     *     
     */
    public void setAuthor(Author value) {
        this.author = value;
    }

    /**
     * Gets the value of the contributors property.
     * 
     * @return
     *     possible object is
     *     {@link Contributors }
     *     
     */
    public Contributors getContributors() {
        return contributors;
    }

    /**
     * Sets the value of the contributors property.
     * 
     * @param value
     *     allowed object is
     *     {@link Contributors }
     *     
     */
    public void setContributors(Contributors value) {
        this.contributors = value;
    }

    /**
     * Gets the value of the volume property.
     * 
     * @return
     *     possible object is
     *     {@link Volume }
     *     
     */
    public Volume getVolume() {
        return volume;
    }

    /**
     * Sets the value of the volume property.
     * 
     * @param value
     *     allowed object is
     *     {@link Volume }
     *     
     */
    public void setVolume(Volume value) {
        this.volume = value;
    }

    /**
     * Gets the value of the issue property.
     * 
     * @return
     *     possible object is
     *     {@link Issue }
     *     
     */
    public Issue getIssue() {
        return issue;
    }

    /**
     * Sets the value of the issue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Issue }
     *     
     */
    public void setIssue(Issue value) {
        this.issue = value;
    }

    /**
     * Gets the value of the firstPage property.
     * 
     * @return
     *     possible object is
     *     {@link FirstPage }
     *     
     */
    public FirstPage getFirstPage() {
        return firstPage;
    }

    /**
     * Sets the value of the firstPage property.
     * 
     * @param value
     *     allowed object is
     *     {@link FirstPage }
     *     
     */
    public void setFirstPage(FirstPage value) {
        this.firstPage = value;
    }

    /**
     * Gets the value of the itemNumber property.
     * 
     * @return
     *     possible object is
     *     {@link ItemNumber }
     *     
     */
    public ItemNumber getItemNumber() {
        return itemNumber;
    }

    /**
     * Sets the value of the itemNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link ItemNumber }
     *     
     */
    public void setItemNumber(ItemNumber value) {
        this.itemNumber = value;
    }

    /**
     * Gets the value of the lastPage property.
     * 
     * @return
     *     possible object is
     *     {@link LastPage }
     *     
     */
    public LastPage getLastPage() {
        return lastPage;
    }

    /**
     * Sets the value of the lastPage property.
     * 
     * @param value
     *     allowed object is
     *     {@link LastPage }
     *     
     */
    public void setLastPage(LastPage value) {
        this.lastPage = value;
    }

    /**
     * Gets the value of the editionNumber property.
     * 
     * @return
     *     possible object is
     *     {@link EditionNumber }
     *     
     */
    public EditionNumber getEditionNumber() {
        return editionNumber;
    }

    /**
     * Sets the value of the editionNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link EditionNumber }
     *     
     */
    public void setEditionNumber(EditionNumber value) {
        this.editionNumber = value;
    }

    /**
     * Gets the value of the componentNumber property.
     * 
     * @return
     *     possible object is
     *     {@link ComponentNumber }
     *     
     */
    public ComponentNumber getComponentNumber() {
        return componentNumber;
    }

    /**
     * Sets the value of the componentNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComponentNumber }
     *     
     */
    public void setComponentNumber(ComponentNumber value) {
        this.componentNumber = value;
    }

    /**
     * Gets the value of the year property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the year property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getYear().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Year }
     * 
     * 
     */
    public List<Year> getYear() {
        if (year == null) {
            year = new ArrayList<Year>();
        }
        return this.year;
    }

    /**
     * Gets the value of the publicationType property.
     * 
     * @return
     *     possible object is
     *     {@link PublicationType }
     *     
     */
    public PublicationType getPublicationType() {
        return publicationType;
    }

    /**
     * Sets the value of the publicationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link PublicationType }
     *     
     */
    public void setPublicationType(PublicationType value) {
        this.publicationType = value;
    }

    /**
     * Gets the value of the articleTitle property.
     * 
     * @return
     *     possible object is
     *     {@link ArticleTitle }
     *     
     */
    public ArticleTitle getArticleTitle() {
        return articleTitle;
    }

    /**
     * Sets the value of the articleTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArticleTitle }
     *     
     */
    public void setArticleTitle(ArticleTitle value) {
        this.articleTitle = value;
    }

    /**
     * Gets the value of the institutionName property.
     * 
     * @return
     *     possible object is
     *     {@link InstitutionName }
     *     
     */
    public InstitutionName getInstitutionName() {
        return institutionName;
    }

    /**
     * Sets the value of the institutionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link InstitutionName }
     *     
     */
    public void setInstitutionName(InstitutionName value) {
        this.institutionName = value;
    }

    /**
     * Gets the value of the identifier property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the identifier property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdentifier().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Identifier }
     * 
     * 
     */
    public List<Identifier> getIdentifier() {
        if (identifier == null) {
            identifier = new ArrayList<Identifier>();
        }
        return this.identifier;
    }

    /**
     * Gets the value of the componentList property.
     * 
     * @return
     *     possible object is
     *     {@link ComponentList }
     *     
     */
    public ComponentList getComponentList() {
        return componentList;
    }

    /**
     * Sets the value of the componentList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComponentList }
     *     
     */
    public void setComponentList(ComponentList value) {
        this.componentList = value;
    }

    /**
     * Gets the value of the crmItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crmItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrmItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmItem }
     * 
     * 
     */
    public List<CrmItem> getCrmItem() {
        if (crmItem == null) {
            crmItem = new ArrayList<CrmItem>();
        }
        return this.crmItem;
    }

    /**
     * Gets the value of the doiRecord property.
     * 
     * @return
     *     possible object is
     *     {@link DoiRecord }
     *     
     */
    public DoiRecord getDoiRecord() {
        return doiRecord;
    }

    /**
     * Sets the value of the doiRecord property.
     * 
     * @param value
     *     allowed object is
     *     {@link DoiRecord }
     *     
     */
    public void setDoiRecord(DoiRecord value) {
        this.doiRecord = value;
    }

    /**
     * Gets the value of the key property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the value of the key property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKey(String value) {
        this.key = value;
    }

    /**
     * Gets the value of the enableMultipleHits property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isEnableMultipleHits() {
        if (enableMultipleHits == null) {
            return false;
        } else {
            return enableMultipleHits;
        }
    }

    /**
     * Sets the value of the enableMultipleHits property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEnableMultipleHits(Boolean value) {
        this.enableMultipleHits = value;
    }

    /**
     * Gets the value of the forwardMatch property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isForwardMatch() {
        if (forwardMatch == null) {
            return false;
        } else {
            return forwardMatch;
        }
    }

    /**
     * Sets the value of the forwardMatch property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setForwardMatch(Boolean value) {
        this.forwardMatch = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the flCount property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFlCount() {
        return flCount;
    }

    /**
     * Sets the value of the flCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFlCount(BigInteger value) {
        this.flCount = value;
    }

    /**
     * Gets the value of the queryMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQueryMode() {
        return queryMode;
    }

    /**
     * Sets the value of the queryMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQueryMode(String value) {
        this.queryMode = value;
    }

}