import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import model.Book;
import model.BookList;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Используя библиотеку Jackson, напишите Java-программу, которая вычитывает JSON-файл(books.json) и сохранит данные в коллекцию Java.
 * Конвертируйте данные из этой коллекции в XML-формат с помощью библиотеки JAXB. Сохраните полученный XML результат в файл.
 */

public class ApplicationRunner {
    public static void main(String[] args) {
        try {

            File jsonFile = new File("books.json");
            ObjectMapper objectMapper = new ObjectMapper();
            List<Book> books = objectMapper.readValue(jsonFile, new TypeReference<List<Book>>() {});

            BookList bookList = new BookList(books);

            File xmlFile = new File("books.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(BookList.class);
            Marshaller marshaller = jaxbContext.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(bookList, xmlFile);

            System.out.println("Данные успешно сохранены в файл books.xml!");

        } catch (IOException e) {
            System.err.println("Ошибка чтения файла JSON: " + e.getMessage());
        } catch (JAXBException e) {
            System.err.println("Ошибка при конвертации в XML: " + e.getMessage());
        }
    }
}
