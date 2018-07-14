
package cn.itcast.crm.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cn.itcast.crm.service package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _FindCustomerByTelephoneResponse_QNAME = new QName("http://service.crm.itcast.cn/", "findCustomerByTelephoneResponse");
    private final static QName _FindhasassociationCustomersResponse_QNAME = new QName("http://service.crm.itcast.cn/", "findhasassociationCustomersResponse");
    private final static QName _FindnoassociationCustomersResponse_QNAME = new QName("http://service.crm.itcast.cn/", "findnoassociationCustomersResponse");
    private final static QName _FindDecidedzoneIdByAddress_QNAME = new QName("http://service.crm.itcast.cn/", "findDecidedzoneIdByAddress");
    private final static QName _AssignCustomersToDecidedZone_QNAME = new QName("http://service.crm.itcast.cn/", "assignCustomersToDecidedZone");
    private final static QName _AssignCustomersToDecidedZoneResponse_QNAME = new QName("http://service.crm.itcast.cn/", "assignCustomersToDecidedZoneResponse");
    private final static QName _FindhasassociationCustomers_QNAME = new QName("http://service.crm.itcast.cn/", "findhasassociationCustomers");
    private final static QName _FindnoassociationCustomers_QNAME = new QName("http://service.crm.itcast.cn/", "findnoassociationCustomers");
    private final static QName _FindCustomerByTelephone_QNAME = new QName("http://service.crm.itcast.cn/", "findCustomerByTelephone");
    private final static QName _FindDecidedzoneIdByAddressResponse_QNAME = new QName("http://service.crm.itcast.cn/", "findDecidedzoneIdByAddressResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cn.itcast.crm.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FindhasassociationCustomersResponse }
     * 
     */
    public FindhasassociationCustomersResponse createFindhasassociationCustomersResponse() {
        return new FindhasassociationCustomersResponse();
    }

    /**
     * Create an instance of {@link FindCustomerByTelephoneResponse }
     * 
     */
    public FindCustomerByTelephoneResponse createFindCustomerByTelephoneResponse() {
        return new FindCustomerByTelephoneResponse();
    }

    /**
     * Create an instance of {@link FindCustomerByTelephone }
     * 
     */
    public FindCustomerByTelephone createFindCustomerByTelephone() {
        return new FindCustomerByTelephone();
    }

    /**
     * Create an instance of {@link FindDecidedzoneIdByAddressResponse }
     * 
     */
    public FindDecidedzoneIdByAddressResponse createFindDecidedzoneIdByAddressResponse() {
        return new FindDecidedzoneIdByAddressResponse();
    }

    /**
     * Create an instance of {@link FindnoassociationCustomers }
     * 
     */
    public FindnoassociationCustomers createFindnoassociationCustomers() {
        return new FindnoassociationCustomers();
    }

    /**
     * Create an instance of {@link FindhasassociationCustomers }
     * 
     */
    public FindhasassociationCustomers createFindhasassociationCustomers() {
        return new FindhasassociationCustomers();
    }

    /**
     * Create an instance of {@link AssignCustomersToDecidedZoneResponse }
     * 
     */
    public AssignCustomersToDecidedZoneResponse createAssignCustomersToDecidedZoneResponse() {
        return new AssignCustomersToDecidedZoneResponse();
    }

    /**
     * Create an instance of {@link FindDecidedzoneIdByAddress }
     * 
     */
    public FindDecidedzoneIdByAddress createFindDecidedzoneIdByAddress() {
        return new FindDecidedzoneIdByAddress();
    }

    /**
     * Create an instance of {@link AssignCustomersToDecidedZone }
     * 
     */
    public AssignCustomersToDecidedZone createAssignCustomersToDecidedZone() {
        return new AssignCustomersToDecidedZone();
    }

    /**
     * Create an instance of {@link FindnoassociationCustomersResponse }
     * 
     */
    public FindnoassociationCustomersResponse createFindnoassociationCustomersResponse() {
        return new FindnoassociationCustomersResponse();
    }

    /**
     * Create an instance of {@link Customer }
     * 
     */
    public Customer createCustomer() {
        return new Customer();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindCustomerByTelephoneResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm.itcast.cn/", name = "findCustomerByTelephoneResponse")
    public JAXBElement<FindCustomerByTelephoneResponse> createFindCustomerByTelephoneResponse(FindCustomerByTelephoneResponse value) {
        return new JAXBElement<FindCustomerByTelephoneResponse>(_FindCustomerByTelephoneResponse_QNAME, FindCustomerByTelephoneResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindhasassociationCustomersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm.itcast.cn/", name = "findhasassociationCustomersResponse")
    public JAXBElement<FindhasassociationCustomersResponse> createFindhasassociationCustomersResponse(FindhasassociationCustomersResponse value) {
        return new JAXBElement<FindhasassociationCustomersResponse>(_FindhasassociationCustomersResponse_QNAME, FindhasassociationCustomersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindnoassociationCustomersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm.itcast.cn/", name = "findnoassociationCustomersResponse")
    public JAXBElement<FindnoassociationCustomersResponse> createFindnoassociationCustomersResponse(FindnoassociationCustomersResponse value) {
        return new JAXBElement<FindnoassociationCustomersResponse>(_FindnoassociationCustomersResponse_QNAME, FindnoassociationCustomersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindDecidedzoneIdByAddress }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm.itcast.cn/", name = "findDecidedzoneIdByAddress")
    public JAXBElement<FindDecidedzoneIdByAddress> createFindDecidedzoneIdByAddress(FindDecidedzoneIdByAddress value) {
        return new JAXBElement<FindDecidedzoneIdByAddress>(_FindDecidedzoneIdByAddress_QNAME, FindDecidedzoneIdByAddress.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssignCustomersToDecidedZone }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm.itcast.cn/", name = "assignCustomersToDecidedZone")
    public JAXBElement<AssignCustomersToDecidedZone> createAssignCustomersToDecidedZone(AssignCustomersToDecidedZone value) {
        return new JAXBElement<AssignCustomersToDecidedZone>(_AssignCustomersToDecidedZone_QNAME, AssignCustomersToDecidedZone.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssignCustomersToDecidedZoneResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm.itcast.cn/", name = "assignCustomersToDecidedZoneResponse")
    public JAXBElement<AssignCustomersToDecidedZoneResponse> createAssignCustomersToDecidedZoneResponse(AssignCustomersToDecidedZoneResponse value) {
        return new JAXBElement<AssignCustomersToDecidedZoneResponse>(_AssignCustomersToDecidedZoneResponse_QNAME, AssignCustomersToDecidedZoneResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindhasassociationCustomers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm.itcast.cn/", name = "findhasassociationCustomers")
    public JAXBElement<FindhasassociationCustomers> createFindhasassociationCustomers(FindhasassociationCustomers value) {
        return new JAXBElement<FindhasassociationCustomers>(_FindhasassociationCustomers_QNAME, FindhasassociationCustomers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindnoassociationCustomers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm.itcast.cn/", name = "findnoassociationCustomers")
    public JAXBElement<FindnoassociationCustomers> createFindnoassociationCustomers(FindnoassociationCustomers value) {
        return new JAXBElement<FindnoassociationCustomers>(_FindnoassociationCustomers_QNAME, FindnoassociationCustomers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindCustomerByTelephone }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm.itcast.cn/", name = "findCustomerByTelephone")
    public JAXBElement<FindCustomerByTelephone> createFindCustomerByTelephone(FindCustomerByTelephone value) {
        return new JAXBElement<FindCustomerByTelephone>(_FindCustomerByTelephone_QNAME, FindCustomerByTelephone.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindDecidedzoneIdByAddressResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm.itcast.cn/", name = "findDecidedzoneIdByAddressResponse")
    public JAXBElement<FindDecidedzoneIdByAddressResponse> createFindDecidedzoneIdByAddressResponse(FindDecidedzoneIdByAddressResponse value) {
        return new JAXBElement<FindDecidedzoneIdByAddressResponse>(_FindDecidedzoneIdByAddressResponse_QNAME, FindDecidedzoneIdByAddressResponse.class, null, value);
    }

}
