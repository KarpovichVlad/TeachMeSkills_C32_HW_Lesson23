package Dom;

import model.Book;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 *  Используя любой парсер(DOM,SAX,StAX) распарсите данные в Java приложении и выведите в консоль информацию о книге с наибольшим количеством страниц.
 */

public class DomParserExample {
    public static void main(String[] args) {
        try {

            File xmlFile = new File("books.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);

            document.getDocumentElement().normalize();

            NodeList bookNodes = document.getElementsByTagName("book");

            Book maxPagesBook = null;
            int maxPages = 0;

            for (int i = 0; i < bookNodes.getLength(); i++) {
                Node node = bookNodes.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element bookElement = (Element) node;

                    String title = bookElement.getElementsByTagName("title").item(0).getTextContent();
                    String author = bookElement.getElementsByTagName("author").item(0).getTextContent();
                    int year = Integer.parseInt(bookElement.getElementsByTagName("year").item(0).getTextContent());
                    int pages = Integer.parseInt(bookElement.getElementsByTagName("pages").item(0).getTextContent());
                    String genre = bookElement.getElementsByTagName("genre").item(0).getTextContent();

                    Book book = new Book(title, author, year, pages, genre);

                    if (pages > maxPages) {
                        maxPages = pages;
                        maxPagesBook = book;
                    }
                }
            }

            if (maxPagesBook != null) {
                System.out.println("Книга с наибольшим количеством страниц:");
                System.out.println(maxPagesBook);
            } else {
                System.out.println("Книги не найдены!");
            }

        } catch (Exception e) {
            System.err.println("Ошибка при разборе XML: " + e.getMessage());
        }
    }
}

