package fr.tln.univ.designPatterns.abstractFactory;

import fr.tln.univ.designPatterns.abstractFactory.media.Converter;
import fr.tln.univ.designPatterns.abstractFactory.media.factory.MediaConverterAbstractFactory;
import fr.tln.univ.designPatterns.abstractFactory.media.factory.MediaFactory;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.File;

public class DocumentBuilderExample {

    public static void main(String[] args) throws Exception {
        /*DocumentBuilderFactory abstractFactory
                = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = abstractFactory.newDocumentBuilder();

        System.out.println(abstractFactory.getClass());
        System.out.println(documentBuilder.getClass());

        byte[] s = ("<person><firstName>Shahin" +
                "</firstName><lastName>HEIDARI</lastName></person>")
                        .getBytes("UTF-8");
        Document parse = documentBuilder
                .parse(new ByteArrayInputStream(s));
        parse.normalizeDocument();
        System.out.println(parse);*/


        MediaFactory factory =
                MediaConverterAbstractFactory.createFactory(MediaConverterAbstractFactory.MediaFactoryType.IMAGE);
        Converter converter =
                factory.createConverter(new File("//h.bmp"), Converter.CodecTypes.JPG);

        System.out.println(factory.getClass());
        System.out.println(converter.getClass());
        byte[] convert = converter.convert();
    }
}
