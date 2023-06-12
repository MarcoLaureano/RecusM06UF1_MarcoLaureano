package com.balmes;

import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;


public class PrincipalLlista {
    public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerException {
        List<ArticlesCompra> llistaCompra = capturarAtributs();
        generarFitxerXML(llistaCompra);
        serialitzarLlistaCompra(llistaCompra);
    }

    private static List<ArticlesCompra> capturarAtributs() throws IOException {
        List<ArticlesCompra> llistaCompra = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        boolean continuar = true;
        while (continuar) {
            System.out.print("Descripció: ");
            String descripcio = reader.readLine();
            System.out.print("Preu: ");
            double preu = Double.parseDouble(reader.readLine());
            System.out.print("Quantitat: ");
            double quantitat = Double.parseDouble(reader.readLine());
            System.out.print("Unitat: ");
            String unitat = reader.readLine();
            System.out.print("Secció: ");
            String seccio = reader.readLine();

            ArticlesCompra article = new ArticlesCompra(descripcio, preu, quantitat, unitat, seccio);
            llistaCompra.add(article);

            System.out.print("Vols afegir un altre article? (S/N): ");
            String resposta = reader.readLine();
            continuar = resposta.equalsIgnoreCase("S");
        }

        return llistaCompra;
    }

    private static void serialitzarLlistaCompra(List<ArticlesCompra> llistaCompra) throws IOException {
        FileOutputStream fos = new FileOutputStream("llistaCompra.xml");
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        XMLEncoder encoder = new XMLEncoder(bos);
        encoder.writeObject(llistaCompra);
        encoder.close();
        bos.close();
        fos.close();
    }

    private static void generarFitxerXML(List<ArticlesCompra> llistaCompra) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.newDocument();
        Element llistaCompraElement = doc.createElement("llistacompra");
        doc.appendChild(llistaCompraElement);

        for (ArticlesCompra article : llistaCompra) {
            Element articleElement = doc.createElement("article");
            llistaCompraElement.appendChild(articleElement);

            createElement(doc, articleElement, "descripcio", article.getDescripcio());
            createElement(doc, articleElement, "preu", String.valueOf(article.getPreu()));
            createElement(doc, articleElement, "quantitat", String.valueOf(article.getQuantitat()));
            createElement(doc, articleElement, "unitat", article.getUnitat());
            createElement(doc, articleElement, "seccio", article.getSeccio());
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(System.out);
        transformer.transform(source, result);
    }

    private static void createElement(Document doc, Element parent, String tagName, String textContent) {
        Element element = doc.createElement(tagName);
        Text text = doc.createTextNode(textContent);
        element.appendChild(text);
        parent.appendChild(element);
    }

}
