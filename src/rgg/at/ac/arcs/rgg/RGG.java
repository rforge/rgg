package at.ac.arcs.rgg;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.JPanel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import at.ac.arcs.rgg.builder.RGGPanelBuilder;
import at.ac.arcs.rgg.factories.RGGFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author ilhami
 */
public class RGG {

    private static CompositeConfiguration config;
    private static Log log = LogFactory.getLog(RGG.class);
    private HashMap<String, Object> idMap = new HashMap<String, Object>();
    private HashMap<String, Object> rggProperties = new HashMap<String, Object>();
    private RGGModel rggModel;
    private RGGPanelModel rggPanelModel;
    private File rGGFile;
    private File rGGFileDir;

    private RGG(File rggFile) {
        this.rGGFile = rggFile;
        this.rGGFileDir = rggFile.getParentFile();
    }

    private static void initRGG() throws ConfigurationException {
        if (config == null) {
            config = new CompositeConfiguration();
            try {
                config.addConfiguration(
                        new PropertiesConfiguration(
                        RGG.class.getResource("/at/ac/arcs/rgg/config/elementfactory.properties")));
                config.addConfiguration(
                        new PropertiesConfiguration(
                        RGG.class.getResource("/at/ac/arcs/rgg/config/rgg-attributes.properties")));
            } catch (ConfigurationException ex) {
                log.fatal("Couldn't initialize RGG", ex.getCause());
                throw ex;
            }
        }
    }

    public static RGG newInstance(File rggFile)
            throws ConfigurationException, ParserConfigurationException, 
            SAXException, IOException, ClassNotFoundException, 
            InstantiationException, IllegalAccessException {
        if (config == null) {
            initRGG();
        }

        RGG rgg = new RGG(rggFile);
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(rggFile);
        Element rggElement = null;
        for (int i = 0; i < document.getChildNodes().getLength(); i++) {
            if (document.getChildNodes().item(i).getNodeType() == Element.ELEMENT_NODE) {
                rggElement = (Element) document.getChildNodes().item(i);
            }
        }

        rgg.setRggModel(RGGFactory.createRGGModel(rggElement, rgg));
        rgg.setRggPanelModel(new RGGPanelModel(rgg.getRggModel()));
        return rgg;
    }

    public static Configuration getConfiguration() {
        if (config == null) {
            throw new IllegalStateException("Please call first initRGG() method of this class!");
        }
        return config;
    }

    public String generateRScript() {
        return rggModel.generateRScript();
    }

    public JPanel buildPanel(boolean useDefaultDialogBorder, boolean debug) {
        return new RGGPanelBuilder().buildPanel(rggPanelModel, useDefaultDialogBorder, debug);
    }

    public void addObject(String id, Object obj) {
        idMap.put(id, obj);
    }

    public Object getObject(String id) {
        return idMap.get(id);
    }

    public void setProperty(String key, Object value) {
        rggProperties.put(key, value);
    }

    public Object getProperty(String key) {
        return rggProperties.get(key);
    }

    public RGGModel getRggModel() {
        return rggModel;
    }

    public void setRggModel(RGGModel rggModel) {
        this.rggModel = rggModel;
    }

    public RGGPanelModel getRggPanelModel() {
        return rggPanelModel;
    }

    public void setRggPanelModel(RGGPanelModel rggPanelModel) {
        this.rggPanelModel = rggPanelModel;
    }

    public File getRGGFileDir() {
        return rGGFileDir;
    }

    public void setRGGFileDir(File rGGFileDir) {
        this.rGGFileDir = rGGFileDir;
    }

    public File getRGGFile() {
        return rGGFile;
    }

    private void setRGGFile(File rGGFile) {
        this.rGGFile = rGGFile;
    }

    public String getRGGName() {
        return rGGFile.getName();
    }
}