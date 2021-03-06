package test.websvc.client.sf.cbta;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.0.5
 * 2015-10-13T17:20:20.428+08:00
 * Generated source version: 3.0.5
 * 
 */
@WebServiceClient(name = "SfexpressServiceImplService", 
                  wsdlLocation = "http://120.24.60.8:8003/CBTA/ws/sfexpressService?wsdl",
                  targetNamespace = "http://impl.appservice.sfexpress.business.ieca.sf.com/") 
public class SfexpressServiceImplService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://impl.appservice.sfexpress.business.ieca.sf.com/", "SfexpressServiceImplService");
    public final static QName SfexpressServiceImplPort = new QName("http://impl.appservice.sfexpress.business.ieca.sf.com/", "SfexpressServiceImplPort");
    static {
        URL url = null;
        try {
            url = new URL("http://120.24.60.8:8003/CBTA/ws/sfexpressService?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(SfexpressServiceImplService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://120.24.60.8:8003/CBTA/ws/sfexpressService?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public SfexpressServiceImplService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public SfexpressServiceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SfexpressServiceImplService() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns SfexpressService
     */
    @WebEndpoint(name = "SfexpressServiceImplPort")
    public SfexpressService getSfexpressServiceImplPort() {
        return super.getPort(SfexpressServiceImplPort, SfexpressService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SfexpressService
     */
    @WebEndpoint(name = "SfexpressServiceImplPort")
    public SfexpressService getSfexpressServiceImplPort(WebServiceFeature... features) {
        return super.getPort(SfexpressServiceImplPort, SfexpressService.class, features);
    }

}
