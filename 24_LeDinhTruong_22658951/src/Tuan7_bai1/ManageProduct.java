package Tuan7_bai1;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class ManageProduct {
    private static String fileName = "product.xml";
    private Document document;

    public ManageProduct() {
        try {
            File file = new File(fileName);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            if (file.exists()) {
                document = builder.parse(file);
            } else {
                document = builder.newDocument();
                Element root = document.createElement("products");
                document.appendChild(root);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addProduct(Product p) {
        Element root = document.getDocumentElement();

        Element pNode = document.createElement("product");
        pNode.setAttribute("id", p.getProductID());

        Element name = document.createElement("name");
        name.setTextContent(p.getName());

        Element manufacture = document.createElement("manufacture");
        manufacture.setTextContent(p.getManufacture());

        Element description = document.createElement("description");
        description.setTextContent(p.getDescription());

        // Supplier
        Element supplier = document.createElement("supplier");

        Element sName = document.createElement("name");
        sName.setTextContent(p.getSupplier().getName());

        Element country = document.createElement("country");
        country.setTextContent(p.getSupplier().getCountry());

        Element website = document.createElement("website");
        website.setTextContent(p.getSupplier().getWebsite());

        supplier.appendChild(sName);
        supplier.appendChild(country);
        supplier.appendChild(website);

        Element price = document.createElement("price");
        price.setTextContent(String.valueOf(p.getPrice()));

        // add vào product
        pNode.appendChild(name);
        pNode.appendChild(manufacture);
        pNode.appendChild(description);
        pNode.appendChild(supplier);
        pNode.appendChild(price);

        root.appendChild(pNode);
    }

    public void deleteProduct(String pid) {
        NodeList list = document.getElementsByTagName("product");

        for (int i = 0; i < list.getLength(); i++) {
            Element p = (Element) list.item(i);
            if (p.getAttribute("id").equals(pid)) {
                p.getParentNode().removeChild(p);
                break;
            }
        }
    }

    public void updatePrice(String pid, double newPrice) {
        NodeList list = document.getElementsByTagName("product");

        for (int i = 0; i < list.getLength(); i++) {
            Element p = (Element) list.item(i);
            if (p.getAttribute("id").equals(pid)) {
                p.getElementsByTagName("price").item(0)
                        .setTextContent(String.valueOf(newPrice));
                break;
            }
        }
    }

    public void writeXMLFile() {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.transform(new DOMSource(document),
                    new StreamResult(new File(fileName)));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printAll() {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.transform(new DOMSource(document),
                    new StreamResult(System.out));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
