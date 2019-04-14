package storage.initor.datasourcereader.stax;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;

public class CustomStaxReader implements AutoCloseable {

    private XMLStreamReader reader = null;
    private FileInputStream fileInputStream;


    private CustomStaxReader() {

    }

    public static CustomStaxReader newInstance(String file) throws Exception {
        CustomStaxReader instance = new CustomStaxReader();
        instance.fileInputStream = new FileInputStream(file);
        instance.reader = XMLInputFactory.newInstance()
                .createXMLStreamReader(instance.fileInputStream);
        return instance;
    }

    @Override
    public void close() throws Exception {
        if (reader != null) {
            reader.close();
        }

        if (fileInputStream != null) {
            fileInputStream.close();
        }
    }


    public XMLStreamReader getReader() {
        return reader;
    }

    public static String readContent(XMLStreamReader reader) throws XMLStreamException {

        StringBuilder stringBuilder = new StringBuilder();

        while (reader.hasNext()) {
            int eventType = reader.next();

            switch (eventType) {
                case XMLStreamConstants.CHARACTERS:
                case XMLStreamConstants.CDATA: {
                    stringBuilder.append(reader.getText());
                    break;
                }

                case XMLStreamConstants.END_ELEMENT: {
                    return stringBuilder.toString();
                }
            }
        }
        throw new RuntimeException("I didn't find suitable end tag");
    }

}